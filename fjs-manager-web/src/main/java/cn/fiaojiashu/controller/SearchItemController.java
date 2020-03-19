package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: SearchItemController
 * @Date: 2020/3/19 15:12
 * @Description:导入商品数据到索引库
 */
@Controller
public class SearchItemController {

    @Autowired(required = false)
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public FiaoJiaShuResult importItemList() {
        FiaoJiaShuResult result = searchItemService.importAllItems();
        return result;
    }
}
