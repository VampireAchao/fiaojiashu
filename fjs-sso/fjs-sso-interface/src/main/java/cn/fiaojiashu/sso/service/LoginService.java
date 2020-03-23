package cn.fiaojiashu.sso.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;

/**
 * @ClassName: LoginService
 * @Date: 2020/3/23 13:20
 * @Description:登录接口
 */
public interface LoginService {

    /**
     *
     * @param username  用户名
     * @param password  密码
     * @return  FiaoJiaShuResult，其中包含token信息
     */
    FiaoJiaShuResult userLogin(String username, String password);


}
