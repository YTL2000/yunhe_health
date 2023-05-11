package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 体检套餐
 */
@ApiModel("体检套餐")
@Data
@TableName("t_setmeal")
public class Setmeal implements Serializable {

    @ApiModelProperty("体检套餐ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("套餐名称")
    private String name;
    @ApiModelProperty("套餐编码")
    private String code;
    @ApiModelProperty("助记码")
    @TableField("helpCode")
    private String helpCode;
    @ApiModelProperty("适用性别")
    private String sex;//套餐适用性别：0不限 1男 2女
    @ApiModelProperty("适用年龄")
    private String age;//套餐适用年龄
    @ApiModelProperty("套餐价格")
    private Float price;//套餐价格
    @ApiModelProperty("注意事项")
    private String remark;
    @ApiModelProperty("说明")
    private String attention;
    @ApiModelProperty("套餐图片")
    private String img;//套餐对应图片存储路径
    @TableField(exist = false)
    private List<CheckGroup> checkGroups;//体检套餐对应的检查组，多对多关系

}
