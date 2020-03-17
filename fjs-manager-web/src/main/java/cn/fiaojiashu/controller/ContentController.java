package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.content.service.ContentService;
import cn.fiaojiashu.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult addContent(TbContent content) {
        FiaoJiaShuResult result = contentService.addContent(content);
        return result;
    }
}
