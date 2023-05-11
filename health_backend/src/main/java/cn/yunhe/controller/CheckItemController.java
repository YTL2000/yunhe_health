package cn.yunhe.controller;

import cn.yunhe.constant.MessageConstant;
import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.pojo.CheckItem;
import cn.yunhe.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import cn.yunhe.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @auther YTL
 * @className CheckItemController
 * since 1.0
 */
@Api("检查项管理模块")
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @ApiOperation("新增检查项")
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS) ;
    }
    @ApiOperation("分页查询检查项")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean  queryPageBean){
         PageResult pageResult= checkItemService.findPage(queryPageBean);
        return pageResult;
    }
    @ApiOperation("根据id删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
      checkItemService.delete(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
    @ApiOperation("查看检查项详情")
    @GetMapping("/findById/{id}")
    public Result update(@PathVariable Integer id){
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }
    @ApiOperation("查看检查项详情")
    @PutMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        checkItemService.edit(checkItem);
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
}
