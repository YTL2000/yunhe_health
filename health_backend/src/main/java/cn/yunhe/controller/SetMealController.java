package cn.yunhe.controller;

import cn.yunhe.constant.MessageConstant;
import cn.yunhe.entity.PageResult;
import cn.yunhe.entity.QueryPageBean;
import cn.yunhe.entity.Result;
import cn.yunhe.exception.BusinessException;
import cn.yunhe.pojo.Setmeal;
import cn.yunhe.service.SetmealService;
import cn.yunhe.util.QiniuUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 检查套餐
 *
 * @version 1.0
 * @auther YTL
 * @className SetMealController
 * since 1.0
 */
@Api("检查套餐管理模块")
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    private SetmealService setmealService;

    @ApiOperation("图片上传预览")
    @PostMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String newName;
        try {
            //获取源文件名
            String originalFilename = imgFile.getOriginalFilename();
            //截取文件后缀
            int i = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(i);
            //重命名
            newName = UUID.randomUUID().toString() + ext;
            //文件上传
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), newName);
        } catch (Exception exception) {
            log.info("上传文件发生异常，e:" + exception.getMessage());
            exception.printStackTrace();
            throw new BusinessException("上传图片失败！");
        }
        return new Result(true, MessageConstant.UPLOAD_SUCCESS, newName);
    }

    @ApiOperation("新增检查套餐")
    @PostMapping("/add")
    public Result add(Integer[] checkgroupIds,@RequestBody Setmeal setmeal) {
        setmealService.add(checkgroupIds,setmeal);
        return new Result(true, MessageConstant.UPLOAD_SUCCESS);
    }
    @ApiOperation("检查套餐分页查询")
    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult=setmealService.findPage(queryPageBean);
        return pageResult;
    }

}
