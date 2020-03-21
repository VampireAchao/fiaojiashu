package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ItemController
 * @Date: 2020/3/12 08:51
 * @Description:商品Controller
 */
@Controller
public class ItemController {
    @Autowired(required = false)
    private ItemService itemService;

    /**
     * 根据id查询商品
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    /**
     * 获取商品列表
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //调用服务查询商品列表
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult addItem(TbItem item, String desc) {
        FiaoJiaShuResult result = itemService.addItem(item, desc);
        return result;
    }

}
