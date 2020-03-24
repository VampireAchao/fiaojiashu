package cn.fiaojiashu.cart.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbItem;

import java.util.List;

/**
 * @ClassName: CartService
 * @Date: 2020/3/24 16:25
 * @Description:购物车业务接口
 */
public interface CartService {

    //添加购物车
    FiaoJiaShuResult addCart(long userId, long itemId, int num);

    //合并购物车
    FiaoJiaShuResult mergeCart(long userId, List<TbItem> itemList);

    //取购物车列表
    List<TbItem> getCartList(long userId);

    //更新购物车商品数量
    FiaoJiaShuResult updateCartNum(long userId, long itemId, int num);

    //删除购物车商品
    FiaoJiaShuResult deleteCartItem(long userId, long itemId);
}
