package cn.fiaojiashu.service;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @ClassName: ItemCatService
 * @Date: 2020/3/15 14:18
 * @Description:商品分类业务接口
 */
public interface ItemCatService {
    /**
     * 获取所有商品分类
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getItemCatList(long parentId);
}
