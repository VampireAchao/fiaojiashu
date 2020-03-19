package cn.fiaojiashu.search.mapper;

import cn.fiaojiashu.common.pojo.SearchItem;

import java.util.List;

/**
 * @ClassName: ItemMapper
 * @Date: 2020/3/19 14:01
 * @Description:商品Mapper，用于查询商品导入索引
 */
public interface ItemMapper {
    //获取商品列表
    List<SearchItem> getItemList();
}
