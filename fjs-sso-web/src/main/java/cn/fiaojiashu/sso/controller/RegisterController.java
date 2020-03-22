package cn.fiaojiashu.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: RegisterController
 * @Date: 2020/3/22 17:47
 * @Description:注册功能Controller
 */
@Controller
public class RegisterController {
    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }
}
