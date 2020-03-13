package cn.fiaojiashu.service.impl;

import cn.fiaojiashu.mapper.TbItemMapper;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemExample;
import cn.fiaojiashu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public TbItem geiItemById(long itemId) {
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
            return list.get(0);
        }
        return null;
    }
}
