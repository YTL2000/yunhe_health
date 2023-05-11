package cn.yunhe.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_checkgroup_checkitem")
public class CheckGroupAndItem implements Serializable {
    private Integer checkgroupId;
    private Integer checkitemId;
}
