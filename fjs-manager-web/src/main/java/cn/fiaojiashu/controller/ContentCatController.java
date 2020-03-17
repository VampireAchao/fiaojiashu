package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
