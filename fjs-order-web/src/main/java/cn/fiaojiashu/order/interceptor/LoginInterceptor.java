package cn.fiaojiashu.order.interceptor;

import cn.fiaojiashu.cart.service.CartService;
import cn.fiaojiashu.common.util.CookieUtils;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbUser;
import cn.fiaojiashu.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Date: 2020/3/25 14:45
 * @Description:用户登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${SSO_URL}")
    private String SSO_URL;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        //判断token是否存在
        if (StringUtils.isBlank(token)) {
            //如果token不存在，未登录状态，跳转到sso登录页面，用户登录成功后跳转到当前请求的url
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            //拦截
            return false;
        }
        //如果token存在，调用sso服务，根据token取用户信息
        FiaoJiaShuResult result = tokenService.getUserByToken(token);
        if (result.getStatus() != 200) {
            //如果取不到，说明用户登录已经过期，需要用户登录
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
            //拦截
            return false;
        }
        //如果取到用户信息，说明是登录状态，需要把用户写入request
        TbUser user = (TbUser) result.getData();
        request.setAttribute("user", user);
        //判断cookie中是否有购物车数据，如果有，合并到服务端
        String jsonCartList = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isNotBlank(jsonCartList)) {
            //合并购物车
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
