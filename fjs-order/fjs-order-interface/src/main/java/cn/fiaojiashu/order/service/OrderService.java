package cn.fiaojiashu.order.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.order.pojo.OrderInfo;

/**
 * @ClassName: OrderService
 * @Date: 2020/3/25 16:34
 * @Description:订单业务接口
 */
public interface OrderService {
    //创建订单
    FiaoJiaShuResult createOrder(OrderInfo orderInfo);
}
