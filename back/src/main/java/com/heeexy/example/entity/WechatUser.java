package com.heeexy.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.heeexy.example.entity.SuperEntity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangtengcan
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WechatUser extends SuperEntity<WechatUser> {

    private static final long serialVersionUID = 1L;


    private String country;

    private String province;

    private String city;

    private String encryptUserName;

    private String nickName;

    private String headImg;

    private String signature;

    private String userName;

    private String alias;



}
