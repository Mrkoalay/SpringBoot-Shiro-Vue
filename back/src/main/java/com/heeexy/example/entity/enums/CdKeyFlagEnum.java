package com.heeexy.example.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CdKeyFlagEnum {
    UN_USED(0,"未激活"),
    USED(1,"已激活");

    @EnumValue
    private int flag;
    private String desc;

    CdKeyFlagEnum(final int flag,final String desc){
        this.flag = flag;
        this.desc = desc;
    }
    public Integer getFlag() {
        return this.flag;
    }
    @JsonValue
    public String getDesc(){
        return this.desc;
    }

}

