package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentService;
import cn.fiaojiashu.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ContentController
 * @Date: 2020/3/17 23:15
 * @Description:内容管理controller
 */
@Controller
public class ContentController {
    @Autowired(required = false)
    private ContentService contentService;

    /**
     * 内容添加
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult addContent(TbContent content) {
        FiaoJiaShuResult result = contentService.addContent(content);
        return result;
    }

    /**
     * 分页查询内容列表
     *
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Integer categoryId, Integer page, Integer rows) {
        EasyUIDataGridResult result = contentService.getContentListByCidPageRows(categoryId, page, rows);
        if (result == null) {
            return null;
        }
        return result;
    }

    /**
     * 删除内容分类内的内容...
     * @param ids
     * @return
     */
    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult addContent(String ids) {
        FiaoJiaShuResult result = new FiaoJiaShuResult(201);
        if (StringUtils.isBlank(ids)) {
            return result;
        }
        String[] strings = ids.split(",");
        for (String string : strings) {
            long id = Long.parseLong(string);
            result = contentService.deleteContent(id);
            if (result.getStatus() != 200) {
                break;
            }
        }
        return result;
    }

    /**
     * 修改内容
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/edit", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult editContent(TbContent content) {
        FiaoJiaShuResult result = contentService.updateContent(content);
        return result;
    }

}
