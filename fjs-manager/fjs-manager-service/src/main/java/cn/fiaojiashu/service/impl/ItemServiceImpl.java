package cn.fiaojiashu.service.impl;

import cn.fiaojiashu.common.jedis.JedisClient;
import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.IDUtils;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.mapper.TbItemDescMapper;
import cn.fiaojiashu.mapper.TbItemMapper;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemDesc;
import cn.fiaojiashu.pojo.TbItemDescExample;
import cn.fiaojiashu.pojo.TbItemExample;
import cn.fiaojiashu.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemServiceImpl
 * @Date: 2020/3/12 08:20
 * @Description:商品管理service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource
    private Destination topicDestination;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_PRE}")
    private String REDIS_ITEM_PRE;
    @Value("${ITEM_CACHE_EXPIRE}")
    private Integer ITEM_CACHE_EXPIRE;

    @Override
    public TbItem getItemById(long itemId) {
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + itemId + ":BASE");
            if (StringUtils.isNotBlank(json)) {
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                return tbItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //缓存中没有，查询数据库
        //根据主键查询
//        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        //根据条件查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(itemId);
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            //把结果添加到缓存
            try {
                jedisClient.set(REDIS_ITEM_PRE + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
                //设置过期时间
                jedisClient.expire(REDIS_ITEM_PRE + itemId + ":BASE", ITEM_CACHE_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + itemId + ":DESC");
            if (StringUtils.isNotBlank(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        //把结果添加到缓存
        try {
            jedisClient.set(REDIS_ITEM_PRE + itemId + ":DESC", JsonUtils.objectToJson(tbItemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_PRE + itemId + ":DESC", ITEM_CACHE_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItemDesc;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public FiaoJiaShuResult addItem(TbItem item, String desc) {
        //生成商品id
        final long itemId = IDUtils.genItemId();
        //补全item的属性
        item.setId(itemId);
        item.setStatus((byte) 1);      //1-正常，2-下架，3-删除
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemMapper.insert(item);
        //创建一个商品描述表对应的pojo对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        //补全属性
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        //向商品描述表插入数据
        itemDescMapper.insert(tbItemDesc);
        //发送一个商品添加消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                return textMessage;
            }
        });
        //返回成功
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult updateItem(TbItem item, String desc) {
        //获取商品id
        final long itemId = item.getId();
        if (item.getPrice() != null) {
            item.setPrice(item.getPrice() / 10);
        }
        //设置item的更新时间
        item.setUpdated(new Date());
        //修改商品表数据
        TbItemExample itemExample = new TbItemExample();
        TbItemExample.Criteria itemExampleCriteria = itemExample.createCriteria();
        itemExampleCriteria.andIdEqualTo(itemId);
        itemMapper.updateByExampleSelective(item, itemExample);
        //创建一个商品描述表对应的pojo对象
        TbItemDesc itemDesc = new TbItemDesc();
        //补全属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        //修改商品描述表数据
        TbItemDescExample itemDescExample = new TbItemDescExample();
        TbItemDescExample.Criteria itemDescExampleCriteria = itemDescExample.createCriteria();
        itemDescExampleCriteria.andItemIdEqualTo(itemId);
        itemDescMapper.updateByExampleSelective(itemDesc, itemDescExample);
        //把结果添加到缓存
        try {
            jedisClient.del(REDIS_ITEM_PRE + itemId + ":BASE");
            jedisClient.set(REDIS_ITEM_PRE + itemId + ":BASE", JsonUtils.objectToJson(item));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_PRE + itemId + ":BASE", ITEM_CACHE_EXPIRE);
            jedisClient.del(REDIS_ITEM_PRE + itemId + ":DESC");
            jedisClient.set(REDIS_ITEM_PRE + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_PRE + itemId + ":DESC", ITEM_CACHE_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送一个商品修改消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                return textMessage;
            }
        });
        //返回成功
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult deleteItem(final Long itemId) {
        //判断id是否为空
        if (itemId == null) {
            return FiaoJiaShuResult.build(201,"id不能为空");
        }
        //删除数据库中的商品
        itemMapper.deleteByPrimaryKey(itemId);
        //删除数据库中的商品描述
        itemDescMapper.deleteByPrimaryKey(itemId);
        //删除缓存中的数据
        try {
            jedisClient.del(REDIS_ITEM_PRE + itemId + ":BASE");
            jedisClient.del(REDIS_ITEM_PRE + itemId + ":DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送一个商品删除消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("DELETE:" + itemId);
                return textMessage;
            }
        });
        //返回成功
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult instockItem(final TbItem item) {
        //更改商品更新时间
        item.setUpdated(new Date());
        //1-正常，2-下架，3-删除
        item.setStatus((byte) 2);
        //删除缓存中的数据
        try {
            jedisClient.del(REDIS_ITEM_PRE + item.getId() + ":BASE");
            jedisClient.del(REDIS_ITEM_PRE + item.getId() + ":DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemMapper.updateByPrimaryKeySelective(item);
        //发送一个商品删除消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("DELETE:" + item.getId());
                return textMessage;
            }
        });
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult reshelfItem(final TbItem item) {
        //更改商品更新时间
        item.setUpdated(new Date());
        //1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        itemMapper.updateByPrimaryKeySelective(item);
        System.out.println(item.getId());
        //发送一个商品修改消息
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(item.getId() + "");
                return textMessage;
            }
        });
        return FiaoJiaShuResult.ok();
    }
}
