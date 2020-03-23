package cn.fiaojiashu.sso.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbUser;

/**
 * @ClassName: RegisterService
 * @Date: 2020/3/23 11:13
 * @Description:注册接口
 */
public interface RegisterService {

    //校验参数是否存在
    FiaoJiaShuResult checkData(String param, int type);

    //注册
    FiaoJiaShuResult register(TbUser user);

}
