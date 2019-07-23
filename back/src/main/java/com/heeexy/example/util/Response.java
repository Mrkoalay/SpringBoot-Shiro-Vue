package com.heeexy.example.util;


import com.heeexy.example.entity.enums.I18nEnum;

import java.util.HashMap;

/**
 * @Author yangtc
 * @Description 返回数据
 * @Date 20:11 2018/12/17
 * @Param
 * @return
 **/
public class Response extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static String CODE = "code";
    private static String MSG = "msg";
    private static String DATA = "data";


    public Response() {
        put(CODE, I18nEnum.SUCCESS.getValue());
    }

    public static Response error() {
        return error(I18nEnum.SERVER_ERROR.getValue(), I18nEnum.SERVER_ERROR.getDesc());
    }

    public static Response error(String msg) {
        return error(I18nEnum.SERVER_ERROR.getValue(), msg);
    }



    public static Response error(int code, String msg) {
        Response response = new Response();
        response.put(CODE, code);
        response.put(MSG, msg);
        return response;
    }

    public static Response success(int code, String msg) {
        Response response = new Response();
        response.put(CODE, code);
        response.put(MSG, msg);
        return response;
    }

    public static Response success() {
        return new Response();
    }

    public Response put(Object value) {
        super.put(DATA, value);
        return this;
    }

}
