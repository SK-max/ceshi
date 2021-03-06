package com.usian.controller;

import com.usian.feign.ItemServiceFeignClient;

import com.usian.pojo.TbItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 枫柚素主
 * @version 1.0
 * @date 2020/5/17 15:54
 */

@RestController
@RequestMapping("/backend/item")
public class ItemController {

    @Autowired
    private ItemServiceFeignClient itemServiceFeignClient;

    @RequestMapping("/selectItemInfo")
    public Result selecctItemInfo(Long itemId) {
        TbItem tbItem = itemServiceFeignClient.selectItemInfo(itemId);
        if (tbItem != null) {
            System.out.println(tbItem.toString());
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    }

    /**
     * 分页查询商品列表并分页处理
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "3") Long rows) {
        PageResult pageResult = itemServiceFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult.getResult() != null && pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("请求出错");
    }

    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem) {
        if (tbItem != null) {

            itemServiceFeignClient.inserTbItem(tbItem);
            return Result.ok("好的");
        }
        return Result.error("空的");
    }

    @RequestMapping("/deleteItemById")
    public Result deleteItemById(@RequestParam Long itemId ){
            if(itemId!=null){
                System.out.println(itemId);
                itemServiceFeignClient.deleteItemById(itemId);
                return Result.ok("好的");
            }
        return Result.error("查无结果");
    }
}
