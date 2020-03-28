package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ContentCatController
 * @Date: 2020/3/17 20:21
 * @Description:内容分类管理Controller
 */
@Controller
public class ContentCatController {
    @Autowired(required = false)
    private ContentCategoryService contentCategoryService;

    /**
     * 获取内容分类
     *
     * @param parentId
     * @return
     */
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

    /**
     * 添加内容分类
     *
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult createContentCategory(Long parentId, String name) {
        //调用服务添加节点
        FiaoJiaShuResult result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }

    /**
     * 删除内容分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/category/delete", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult deleteContentCategory(Long id) {
        //调用服务删除节点
        FiaoJiaShuResult result = contentCategoryService.removeContentCategory(id);
        return result;
    }

    /**
     * 重命名内容分类
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/content/category/update", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult updateContentCategory(Long id, String name) {
        //调用服务删除节点
        FiaoJiaShuResult result = contentCategoryService.renameContentCategory(id, name);
        return result;
    }


}
