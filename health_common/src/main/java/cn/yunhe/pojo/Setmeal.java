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
import java.util.List;

/**
 * 体检套餐
 */
@ApiModel("体检套餐")

@TableName("t_setmeal")
public class Setmeal implements Serializable {

    @ApiModelProperty("体检套餐ID")
    @TableId(value = "id", type = IdType.AUTO)
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

    public Setmeal() {
    }

    public Setmeal(Integer id, String name, String code, String helpCode, String sex, String age, Float price, String remark, String attention, String img, List<CheckGroup> checkGroups) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.helpCode = helpCode;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.remark = remark;
        this.attention = attention;
        this.img = img;
        this.checkGroups = checkGroups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<CheckGroup> getCheckGroups() {
        return checkGroups;
    }

    public void setCheckGroups(List<CheckGroup> checkGroups) {
        this.checkGroups = checkGroups;
    }

    @Override
    public String toString() {
        return "Setmeal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", helpCode='" + helpCode + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", attention='" + attention + '\'' +
                ", img='" + img + '\'' +
                ", checkGroups=" + checkGroups +
                '}';
    }
}
