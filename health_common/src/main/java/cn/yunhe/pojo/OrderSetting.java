package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
@ApiModel("预约设置")
@TableName("t_ordersetting")
@Data
public class OrderSetting implements Serializable{

    @ApiModelProperty("预约设置ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;
    @ApiModelProperty("预约设置日期")
    @TableField("orderDate")
    private Date orderDate;//预约设置日期
    @ApiModelProperty("可预约人数")
    private int number;//可预约人数
    @ApiModelProperty("已预约人数")
    private int reservations ;//已预约人数

    public OrderSetting(){

    }

    public OrderSetting(Date orderDate,int number){
        this.orderDate = orderDate;
        this.number = number;
    }

}
