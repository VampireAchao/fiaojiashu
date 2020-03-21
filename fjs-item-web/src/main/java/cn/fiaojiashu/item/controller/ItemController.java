package cn.fiaojiashu.item.controller;

import cn.fiaojiashu.item.pojo.Item;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemDesc;
import cn.fiaojiashu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ItemController
 * @Date: 2020/3/21 14:48
 * @Description:商品详情页面展示Controller
 */
@Controller
public class ItemController {

    @Autowired(required = false)
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {
        //调用服务取商品基本信息
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        //取商品描述信息
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        //把信息传递给页面
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        //返回一个逻辑视图
        return "item";
    }
}
