package cn.fiaojiashu.portal.controller;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
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
    @Value("${CONTENT_BIG_TITLE}")
    private Long CONTENT_BIG_TITLE;
    @Value("${CONTENT_SETINTERVAL_RIGHT_IMG}")
    private Long CONTENT_SETINTERVAL_RIGHT_IMG;
    @Value("${CONTENT_BIG_PERFECT}")
    private Long CONTENT_BIG_PERFECT;
    @Value("${CONTENT_SMALL_PERFECT}")
    private Long CONTENT_SMALL_PERFECT;
    @Value("${CONTENT_RIGHT_PERFECT}")
    private Long CONTENT_RIGHT_PERFECT;
    @Value("${CONTENT_GOODS}")
    private Long CONTENT_GOODS;
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
        List<TbContent> ad2List = contentService.getContentListByCid(CONTENT_BIG_TITLE);
        List<TbContent> ad3List = contentService.getContentListByCid(CONTENT_SETINTERVAL_RIGHT_IMG);
        List<TbContent> ad4List = contentService.getContentListByCid(CONTENT_BIG_PERFECT);
        List<TbContent> ad5List = contentService.getContentListByCid(CONTENT_SMALL_PERFECT);
        List<TbContent> ad6List = contentService.getContentListByCid(CONTENT_RIGHT_PERFECT);
        List<TbContent> ad7List = contentService.getContentListByCid(CONTENT_GOODS);
        //把结果传递给页面
        model.addAttribute("CONTENT_SETINTERVAL",ad1List);
        model.addAttribute("CONTENT_BIG_TITLE",ad2List);
        model.addAttribute("CONTENT_SETINTERVAL_RIGHT_IMG",ad3List);
        model.addAttribute("CONTENT_BIG_PERFECT",ad4List);
        model.addAttribute("CONTENT_SMALL_PERFECT",ad5List);
        model.addAttribute("CONTENT_RIGHT_PERFECT",ad6List);
        model.addAttribute("CONTENT_GOODS",ad7List);
        return "index";
    }

}
