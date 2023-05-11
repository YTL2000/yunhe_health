package cn.yunhe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回结果
 * @version 1.0
 * @auther YTL
 * @className Result
 * since 1.0
 */
@ApiModel("返回结果")
@Data
public class Result implements Serializable {
    @ApiModelProperty("执行是否成功,true为执行成功 false为执行失败")
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    @ApiModelProperty("提示信息，主要用于页面提示信息")
    private String message;//返回提示信息，主要用于页面提示信息
    @ApiModelProperty("返回的数据")
    private Object data;//返回数据
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

}
