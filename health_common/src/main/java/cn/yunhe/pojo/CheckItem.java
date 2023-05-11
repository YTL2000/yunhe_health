package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 检查项
 */
@ApiModel("检查项")
@Data
@TableName("t_checkitem")
public class CheckItem implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("检查项id")
    private Integer id;//主键
    @ApiModelProperty("项目编码")
    private String code;//项目编码
    @ApiModelProperty("项目名称")
    private String name;//项目名称
    @ApiModelProperty("适用性别")
    private String sex;//适用性别
    @ApiModelProperty("适用年龄")
    private String age;//适用年龄（范围），例如：20-50
    @ApiModelProperty("项目价格")
    private Float price;//价格
    @ApiModelProperty("检查项类型：检查、检验")
    private String type;//检查项类型，分为检查和检验两种类型
    @ApiModelProperty("项目说明")
    private String remark;//项目说明
    @ApiModelProperty("注意事项")
    private String attention;//注意事项

}
