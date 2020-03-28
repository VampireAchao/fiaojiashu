package cn.fiaojiashu.content.service.impl;

import cn.fiaojiashu.common.jedis.JedisClient;
import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.content.service.ContentService;
import cn.fiaojiashu.mapper.TbContentMapper;
import cn.fiaojiashu.pojo.TbContent;
import cn.fiaojiashu.pojo.TbContentExample;
import cn.fiaojiashu.pojo.TbContentExample.Criteria;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Date: 2020/3/17 23:11
 * @Description:内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    /**
     * 内容添加
     *
     * @param content
     * @return
     */
    @Override
    public FiaoJiaShuResult addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.insert(content);
        //缓存同步，删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        //返回结果
        return FiaoJiaShuResult.ok();
    }

    /**
     * 根据内容分类id查询列表
     *
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        //查询缓存
        try {
            //如果缓存中有就直接返回结果
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有，查询数据库
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        //把结果添加到缓存
        try {
            jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据内容分类id分页查询列表
     *
     * @param cid
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getContentListByCidPageRows(long cid, int page, int rows) {
        //如果没有，查询数据库
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        //设置分页信息
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //取分页结果
        PageInfo<TbContent> pageInfo = new PageInfo<>();
        //取总记录数
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public FiaoJiaShuResult deleteContent(long id) {
        TbContent content = contentMapper.selectByPrimaryKey(id);
        if (content == null) {
            return FiaoJiaShuResult.build(201, "要删除的内容不存在");
        }
        //删除数据库中的商品
        contentMapper.deleteByPrimaryKey(id);
        //删除缓存中的数据
        try {
            jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回成功
        return FiaoJiaShuResult.ok();
    }

    @Override
    public FiaoJiaShuResult updateContent(TbContent content) {
        //更新数据
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.updateByPrimaryKeyWithBLOBs(content);
        //缓存同步，删除缓存中对应的数据
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        //返回结果
        return FiaoJiaShuResult.ok();
    }
}
