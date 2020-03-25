package cn.fiaojiashu.order.controller;

import cn.fiaojiashu.cart.service.CartService;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.order.pojo.OrderInfo;
import cn.fiaojiashu.order.service.OrderService;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName: OrderController
 * @Date: 2020/3/25 14:00
 * @Description:订单管理Controller
 */
@Controller
public class OrderController {

    @Autowired(required = false)
    private CartService cartService;
    @Autowired(required = false)
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        //取用户id
        TbUser user = (TbUser) request.getAttribute("user");
        //根据用户id取收货地址列表(使用静态数据)
        //获取支付方式列表(静态数据)
        //根据用户id取购物车列表
        List<TbItem> cartList = cartService.getCartList(user.getId());
        //把购物车列表传递给jsp
        request.setAttribute("cartList", cartList);
        //返回页面
        return "order-cart";
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
        //取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        //把用户信息添加到orderInfo中
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        FiaoJiaShuResult result = orderService.createOrder(orderInfo);
        //如果生成成功，需要删除购物车
        if (result.getStatus() == 200) {
            //清空购物车
            cartService.clearCartItem(user.getId());
        }
        //把订单号传递给页面
        request.setAttribute("orderId", result.getData());
        request.setAttribute("payment", orderInfo.getPayment());
        //返回一个逻辑视图
        return "success";
    }

}
