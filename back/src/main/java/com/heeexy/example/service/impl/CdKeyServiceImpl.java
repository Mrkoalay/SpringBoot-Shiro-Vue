package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heeexy.example.dao.CdKeyDao;
import com.heeexy.example.dao.KeyRoleDao;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageFactory;
import com.heeexy.example.util.page.PageParam;
import com.heeexy.example.util.page.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @date: 2017/10/24 16:07
 */
@Service
public class CdKeyServiceImpl extends ServiceImpl<CdKeyDao, CdKey> implements CdKeyService {

    @Autowired
    KeyRoleDao keyRoleDao;

    @Override
    public Response myList(PageParam pageParam, CdKey cdKey) {
        Page page = new PageFactory().buildPage(pageParam);
        Page<CdKey> result = (Page) page(page, new QueryWrapper<>(cdKey));

        List<JSONObject> jsonObjects = keyRoleDao.listRole();
        try {
            result.getRecords().forEach(x -> {
                Integer roleId = x.getRoleId();
                jsonObjects.forEach(y -> {
                    Integer tmpRoleId = y.getIntValue("roleId");
                    if (roleId.intValue() == tmpRoleId) {
                        x.setRoleName(y.getString("roleName"));
                    }
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.success().put(new PageResponse(result));
    }
}
