package cn.fiaojiashu.service.impl;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.mapper.TbItemCatMapper;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemCat;
import cn.fiaojiashu.pojo.TbItemCatExample;
import cn.fiaojiashu.pojo.TbItemCatExample.Criteria;
import cn.fiaojiashu.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ItemCatServiceImpl
 * @Date: 2020/3/15 14:20
 * @Description:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        //根据parentId查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //创建返回结果的List
        List<EasyUITreeNode> resultList = new ArrayList<>();
        //把列表转换成EasyUITreeNode列表
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            //设置属性
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");
            //添加到结果列表
            resultList.add(node);
        }
        //返回结果
        return resultList;
    }
}
