package cn.yunhe.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @auther YTL
 * @className QueryPageBean
 * since 1.0
 */
@ApiModel("分页查询对象")
@Data
public class QueryPageBean implements Serializable {
    @ApiModelProperty("当前页码")
    private Integer currentPage;//页码
    @ApiModelProperty("页码尺寸")
    private Integer pageSize;//每页记录数
    @ApiModelProperty("查询条件")
    private String queryString;//查询条件
}