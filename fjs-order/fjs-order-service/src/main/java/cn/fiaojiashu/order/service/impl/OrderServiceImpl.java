package cn.fiaojiashu.order.service.impl;

import cn.fiaojiashu.common.jedis.JedisClient;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.mapper.TbOrderItemMapper;
import cn.fiaojiashu.mapper.TbOrderMapper;
import cn.fiaojiashu.mapper.TbOrderShippingMapper;
import cn.fiaojiashu.order.pojo.OrderInfo;
import cn.fiaojiashu.order.service.OrderService;
import cn.fiaojiashu.pojo.TbOrderItem;
import cn.fiaojiashu.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Date: 2020/3/25 16:35
 * @Description:订单处理服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired(required = false)
    private JedisClient jedisClient;
    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;
    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN_KEY;

    @Override
    public FiaoJiaShuResult createOrder(OrderInfo orderInfo) {
        //生成订单号，用redis的incr生成
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        //补全OrderInfo属性
        orderInfo.setOrderId(orderId);
        //1、未付款 2、已付款 3、未发货 4、已发货 5、交易成功 6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        //插入订单表
        orderMapper.insert(orderInfo);
        //向订单明细表插入数据
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            //生成明细id
            String orderDetailId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
            //补全pojo属性
            orderItem.setId(orderDetailId);
            orderItem.setOrderId(orderId);
            //向明细表插入数据
            orderItemMapper.insert(orderItem);
        }
        //向订单物流表插入数据
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        //返回FiaoJiaShuResult其中包含订单号
        return FiaoJiaShuResult.ok(orderId);
    }
}
