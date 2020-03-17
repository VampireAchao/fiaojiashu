package cn.fiaojiashu.content.service;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbContent;

import java.util.List;

/**
 * @ClassName: ContentService
 * @Date: 2020/3/17 23:10
 * @Description:内容管理接口
 */
public interface ContentService {
    //添加内容
    FiaoJiaShuResult addContent(TbContent content);

    //根据内容分类id查询列表
    List<TbContent> getContentListByCid(long cid);
}
