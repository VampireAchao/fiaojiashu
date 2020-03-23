package cn.fiaojiashu.sso.service.impl;

import cn.fiaojiashu.common.jedis.JedisClient;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.pojo.TbUser;
import cn.fiaojiashu.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TokenServiceImpl
 * @Date: 2020/3/23 14:57
 * @Description:根据token取用户信息
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public FiaoJiaShuResult getUserByToken(String token) {
        //根据token到redis中取用户信息
        String json = jedisClient.get("SESSION:" + token);
        //如果没有，说明登录已经过期，返回登陆过期
        if (StringUtils.isBlank(json)) {
            return FiaoJiaShuResult.build(201, "用户登录已经过期");
        }
        //如果有，则更新token过期时间
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        //返回结果,FiaoJiaShuResult中包含TbUser对象
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        return FiaoJiaShuResult.ok(user);
    }
}
