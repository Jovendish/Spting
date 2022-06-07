package icu.redamancy.common.model.pojo.cloud;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author redamancy
 * @Date 2022/6/7 14:00
 * @Version 1.0
 */

@Setter
@Getter
@Accessors(chain = true)
@TableName(value = "housenumber")
public class HouseNumber {

    @TableField(value = "housnumber")
    private String housNumber;

    @TableField(value = "unit")
    private Long unit;
    
}
