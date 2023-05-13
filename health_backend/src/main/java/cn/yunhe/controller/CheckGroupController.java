package cn.yunhe.controller;

import cn.yunhe.constant.MessageConstant;
import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.entity.Result;
import cn.yunhe.pojo.CheckGroup;
import cn.yunhe.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.xml.XmlEscapers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @auther YTL
 * @className CheckGroupController
 * since 1.0
 */
@Api("检查组管理模块")
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @ApiOperation("查找所有检查组")
    @GetMapping("findAll")
    public Result findAll(){
        List<CheckGroup> checkGroupList=checkGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
    }

    @ApiOperation("添加检查组")
    @PostMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup) {
        checkGroupService.add(checkitemIds, checkGroup);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @ApiOperation("分页查询")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.findPage(queryPageBean);
        return pageResult;
    }

    @ApiOperation("根据id查询检查组")
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        CheckGroup checkGroup = checkGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }

    @ApiOperation("根据id查询检查组所属检查项")
    @GetMapping("/findCheckItemIdsByCheckGroupId/{id}")
    public Result findCheckItemIdsByCheckGroupId(@PathVariable("id") Integer id) {
        Integer[] checkItemIdsByCheckGroupId = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIdsByCheckGroupId);
    }

    @ApiOperation("更新检查组")
    @PutMapping("/edit")
    public Result edit(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup) {
        checkGroupService.edit(checkitemIds,checkGroup);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    @ApiOperation("更新检查组")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        checkGroupService.delete(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}
