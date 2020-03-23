package cn.fiaojiashu.sso.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;

/**
 * @ClassName: TokenService
 * @Date: 2020/3/23 14:55
 * @Description:根据token查询用户信息
 */
public interface TokenService {

    //查询用户信息
    FiaoJiaShuResult getUserByToken(String token);

}
