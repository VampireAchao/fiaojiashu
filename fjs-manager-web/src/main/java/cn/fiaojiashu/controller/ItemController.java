package cn.fiaojiashu.controller;

import cn.fiaojiashu.common.pojo.EasyUIDataGridResult;
import cn.fiaojiashu.common.util.FiaoJiaShuResult;
import cn.fiaojiashu.pojo.TbItem;
import cn.fiaojiashu.pojo.TbItemDesc;
import cn.fiaojiashu.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加商品
     *
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult addItem(TbItem item, String desc) {
        FiaoJiaShuResult result = itemService.addItem(item, desc);
        return result;
    }

    /**
     * 获取商品描述
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public FiaoJiaShuResult getTbItemDesc(@PathVariable Long itemId) {
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        return FiaoJiaShuResult.ok(itemDesc);
    }

    /**
     * 修改商品
     *
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(value = "/item/update", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult updateItem(TbItem item, String desc) {
        FiaoJiaShuResult result = itemService.updateItem(item, desc);
        return result;
    }

    /**
     * 删除商品
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult deleteItem(String ids) {
        FiaoJiaShuResult result = new FiaoJiaShuResult(201);
        if (StringUtils.isBlank(ids)) {
            return result;
        }
        String[] strings = ids.split(",");
        for (String string : strings) {
            long id = Long.parseLong(string);
            result = itemService.deleteItem(id);
            if (result.getStatus() != 200) {
                break;
            }
        }
        return result;
    }

    @RequestMapping(value = "/item/instock", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult instockItem(String ids) {
        FiaoJiaShuResult result = new FiaoJiaShuResult(201);
        if (StringUtils.isBlank(ids)) {
            return result;
        }
        String[] strings = ids.split(",");
        TbItem item = new TbItem();
        for (String string : strings) {
            long id = Long.parseLong(string);
            item.setId(id);
            result = itemService.instockItem(item);
            if (result.getStatus() != 200) {
                break;
            }
        }
        return result;
    }

    @RequestMapping(value = "/item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public FiaoJiaShuResult reshelfItem(String ids) {
        FiaoJiaShuResult result = new FiaoJiaShuResult(201);
        if (StringUtils.isBlank(ids)) {
            return result;
        }
        String[] strings = ids.split(",");
        TbItem item = new TbItem();
        for (String string : strings) {
            long id = Long.parseLong(string);
            item.setId(id);
            result = itemService.reshelfItem(item);
            if (result.getStatus() != 200) {
                break;
            }
        }
        return result;
    }
}
