package com.heeexy.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yangtc
 * @Description 实体父类
 * @Date 下午2:08 2018/8/16
 * @Param
 * @return
 **/
@Data
@Accessors(chain = true)
public class SuperEntity<T extends Model> extends Model<T> {

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 创建者
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;
    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;
    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 更新者
     */
    private String editor;

    /**
     * 删除标记(0:未删除 1:已删除)
     */
    @TableLogic
    private Integer delStatus;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
