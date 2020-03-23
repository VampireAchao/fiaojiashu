package cn.fiaojiashu.sso.controller;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: TokenController
 * @Date: 2020/3/23 15:09
 * @Description:根据token查询用户信息Controller
 */
@Controller
public class TokenController {

    @Autowired(required = false)
    private TokenService tokenService;

    /*@RequestMapping(value = "/user/token/{token}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE*//*"application/json;charset=utf-8"*//*)
    @ResponseBody
    public String getUserByToken(@PathVariable String token, String callback) {
        FiaoJiaShuResult result = tokenService.getUserByToken(token);
        //相应结果之前判断是否为jsonp请求
        if (StringUtils.isNotBlank(callback)) {
            //把结果封装成js语句响应
            return callback + "(" + JsonUtils.objectToJson(result) + ");";
        }
        return JsonUtils.objectToJson(result);
    }*/
    @RequestMapping(value = "/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        FiaoJiaShuResult result = tokenService.getUserByToken(token);
        //相应结果之前判断是否为jsonp请求
        if (StringUtils.isNotBlank(callback)) {
            //把结果封装成js语句响应
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }
}
