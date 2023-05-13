package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_setmeal_checkgroup")
public class SetmealCheckgroup implements Serializable {
    private Integer setmealId;
    private Integer checkgroupId;


}
