package cn.fiaojiashu.sso.service.impl;

import cn.fiaojiashu.common.jedis.JedisClient;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.common.util.JsonUtils;
import cn.fiaojiashu.mapper.TbUserMapper;
import cn.fiaojiashu.pojo.TbUser;
import cn.fiaojiashu.pojo.TbUserExample;
import cn.fiaojiashu.pojo.TbUserExample.Criteria;
import cn.fiaojiashu.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName: LoginServiceImpl
 * @Date: 2020/3/23 13:25
 * @Description:用户登录处理
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public FiaoJiaShuResult userLogin(String username, String password) {
        //1、判断用户名和密码是否正确
        //根据用户名查询用户信息
        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();
        //设置条件
        criteria.andUsernameEqualTo(username);
        //执行查询
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //返回登陆失败
            return FiaoJiaShuResult.build(400, "用户名或密码错误");
        }
        //取用户信息
        TbUser user = list.get(0);
        //判断密码是否正确
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            //2、如果不正确，返回登陆失败
            return FiaoJiaShuResult.build(400, "用户名或密码错误");
        }
        //3、如果正确则生成token
        String token = UUID.randomUUID().toString();
        //4、把用户信息写入redis，key=token ，value=用户信息
        //防止密码传输到客户端
        user.setPassword(null);
        jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
        //5、设置session过期时间
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        //6、把token返回
        return FiaoJiaShuResult.ok(token);
    }
}
