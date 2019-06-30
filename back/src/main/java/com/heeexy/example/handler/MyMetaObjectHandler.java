package com.heeexy.example.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createdAt = getFieldValByName("createdAt", metaObject);
        Object updatedAt = getFieldValByName("updatedAt", metaObject);
        Object creator = getFieldValByName("creator", metaObject);
        if (createdAt == null) {
            setFieldValByName("createdAt", new Date(), metaObject);
        }
        if (updatedAt == null) {
            setFieldValByName("updatedAt", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedAt", new Date(), metaObject);
    }
}
