package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUITreeNode;
import cn.fiaojiashu.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ItemCatController
 * @Date: 2020/3/15 15:49
 * @Description:商品分类管理Controller
 */
@Controller
public class ItemCatController {
    @Autowired(required = false)
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        //调用服务查询节点列表
        List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }
}
