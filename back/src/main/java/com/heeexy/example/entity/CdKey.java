package com.heeexy.example.entity;

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

	private String flag;


}
