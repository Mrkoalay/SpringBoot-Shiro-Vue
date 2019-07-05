package com.heeexy.example.entity.enums;



/**
 * @Author yangtc
 * @Description // 返回数据枚举
 * @Date 19:39 2018/12/20
 * @Param
 * @return
 **/
public enum I18nEnum {
    SUCCESS(200, "success"),
    SERVER_ERROR(500, "server_error");


    private Integer value;
    private String desc;

    I18nEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }

}
