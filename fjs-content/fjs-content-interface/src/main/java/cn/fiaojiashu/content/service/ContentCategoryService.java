package cn.fiaojiashu.content.service;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;

import java.util.List;

/**
 * @ClassName: ContentCategoryService
 * @Date: 2020/3/17 19:59
 * @Description:内容分类管理接口
 */
public interface ContentCategoryService {
    /**
     * 获取内容分类列表
     *
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getContentCatList(long parentId);

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    FiaoJiaShuResult addContentCategory(long parentId, String name);

    /**
     * 删除内容分类
     * @param id
     * @return
     */
    FiaoJiaShuResult removeContentCategory(long id);

    /**
     * 重命名内容分类
     * @param id
     * @param name
     * @return
     */
    FiaoJiaShuResult renameContentCategory(long id, String name);

}
