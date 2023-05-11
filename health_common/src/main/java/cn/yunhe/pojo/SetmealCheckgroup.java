package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_setmeal_checkgroup")
public class SetmealCheckgroup implements Serializable {
    private Integer setmealId;
    private Integer checkgroupId;
}
