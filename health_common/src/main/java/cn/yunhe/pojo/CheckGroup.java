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
 * 检查组
 */
@ApiModel("检查组")
@Data
@TableName("t_checkgroup")
public class CheckGroup implements Serializable {
    @ApiModelProperty("检查组id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;//主键
    @ApiModelProperty("检查组编码")
    private String code;//编码
    @ApiModelProperty("检查组名称")
    private String name;//名称
    @ApiModelProperty("检查组助记码")
    @TableField("helpCode")
    private String helpCode;//助记 help_code
    @ApiModelProperty("使用性别")
    private String sex;//适用性别
    @ApiModelProperty("说明")
    private String remark;//介绍
    @ApiModelProperty("注意事项")
    private String attention;//注意事项
    @TableField(exist = false)
    private List<CheckItem> checkItems;//一个检查组合包含多个检查项

}
