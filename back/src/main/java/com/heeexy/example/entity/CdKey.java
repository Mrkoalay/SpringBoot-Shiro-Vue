package com.heeexy.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @since 2019-01-13
 */
@Data
@Accessors(chain = true)
public class CdKey extends SuperEntity {

    private static final long serialVersionUID = 1L;

	private String remark;
	private String cdkey;
	private Integer roleId;

	// 0 未使用 1已使用
	private String flag;

	@TableField(exist = false)
	private String roleName;
	@TableField(exist = false)
	private Integer count;


}
