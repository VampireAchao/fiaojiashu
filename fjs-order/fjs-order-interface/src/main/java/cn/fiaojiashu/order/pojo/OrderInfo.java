package cn.fiaojiashu.order.pojo;

import cn.fiaojiashu.pojo.TbOrder;
import cn.fiaojiashu.pojo.TbOrderItem;
import cn.fiaojiashu.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: OrderInfo
 * @Date: 2020/3/25 16:31
 * @Description:订单页表单数据pojo
 */
public class OrderInfo extends TbOrder implements Serializable {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
