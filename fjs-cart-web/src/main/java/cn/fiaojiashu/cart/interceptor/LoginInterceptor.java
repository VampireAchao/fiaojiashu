package cn.fiaojiashu.cart.interceptor;

import cn.fiaojiashu.common.util.CookieUtils;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbUser;
import cn.fiaojiashu.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Date: 2020/3/24 16:03
 * @Description:用户登录处理拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在执行handler前执行此方法
        //返回true：放行，false：拦截
        //1、从cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        //2、如果没有token，未登录状态，直接放行
        if (StringUtils.isBlank(token)) {
            return true;
        }
        //3、取到token，需要调用sso系统服务，根据token取用户信息
        FiaoJiaShuResult result = tokenService.getUserByToken(token);
        //4、没有取到用户信息，登陆过期，直接放行
        if (result.getStatus() != 200) {
            return true;
        }
        //5、取到用户信息，是登录状态
        TbUser user = (TbUser) result.getData();
        //6、把用户信息放入request中，只需要在controller中判断request中是否包含user信息。放行
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //handler执行之后，返回ModelAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //完成处理，返回ModelAndView之后
        //可以在此处理异常
    }
}
