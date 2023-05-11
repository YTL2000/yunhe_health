package cn.yunhe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @auther YTL
 * @className PageResult
 * since 1.0
 */
@ApiModel("分页结果")
@Data
@AllArgsConstructor
public class PageResult implements Serializable {
    @ApiModelProperty("总记录数")
    private Long total;//总记录数

    @ApiModelProperty("当前页的结果集")
    private List rows;//当前页结果
}
