package cn.fiaojiashu.service;

import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbItem;

/**
 * @ClassName: ItemService
 * @Date: 2020/3/12 08:19
 * @Description:商品业务接口
 */
public interface ItemService {
    /**
     * 通过id查询商品
     *
     * @param itemId
     * @return
     */
    TbItem geiItemById(long itemId);

    /**
     * 查询所有商品
     *
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 添加商品
     * @param item
     * @param desc
     * @return
     */
    FiaoJiaShuResult addItem(TbItem item, String desc);

}
