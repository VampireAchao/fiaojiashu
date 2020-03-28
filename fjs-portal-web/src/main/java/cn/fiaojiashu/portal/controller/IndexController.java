package cn.fiaojiashu.portal.controller;

import cn.fiaojiashu.content.service.ContentService;
import cn.fiaojiashu.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: IndexController
 * @Date: 2020/3/17 16:37
 * @Description:首页展示Controller
 */
@Controller
public class IndexController {
    @Value("${CONTENT_SETINTERVAL}")
    private Long CONTENT_SETINTERVAL;
    @Autowired(required = false)
    private ContentService contentService;

    /**
     * 查询首页内容并跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        //查询内容列表
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_SETINTERVAL);
        //把结果传递给页面
        model.addAttribute("CONTENT_SETINTERVAL",ad1List);
        return "index";
    }
}
