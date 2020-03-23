package cn.fiaojiashu.sso.controller;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbUser;
import cn.fiaojiashu.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: RegisterController
 * @Date: 2020/3/22 17:47
 * @Description:注册功能Controller
 */
@Controller
public class RegisterController {

    @Autowired(required = false)
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

    /**
     * 数据有效性校验
     *
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public FiaoJiaShuResult checkData(@PathVariable String param, @PathVariable Integer type) {
        FiaoJiaShuResult result = registerService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult register(TbUser user) {
        FiaoJiaShuResult result = registerService.register(user);
        return result;
    }
}
