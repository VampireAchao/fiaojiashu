package cn.fiaojiashu.sso.controller;

import cn.fiaojiashu.common.util.CookieUtils;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginController
 * @Date: 2020/3/23 12:11
 * @Description:用户登录处理
 */
@Controller
public class LoginController {

    @Autowired(required = false)
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        FiaoJiaShuResult result = loginService.userLogin(username, password);
        //判断是否登陆成功
        if (result.getStatus() == 200) {
            String token = result.getData().toString();
            //如果登陆成功，把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        //返回结果
        return result;
    }

}
