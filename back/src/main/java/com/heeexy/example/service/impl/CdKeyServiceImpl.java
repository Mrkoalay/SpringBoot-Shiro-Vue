package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heeexy.example.dao.CdKeyDao;
import com.heeexy.example.dao.KeyRoleDao;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.entity.MyContainer;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.Docker;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageFactory;
import com.heeexy.example.util.page.PageParam;
import com.heeexy.example.util.page.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @date: 2017/10/24 16:07
 */
@Service
public class CdKeyServiceImpl extends ServiceImpl<CdKeyDao, CdKey> implements CdKeyService {
    private static final Logger logger = LoggerFactory.getLogger(CdKeyServiceImpl.class);
    @Autowired
    KeyRoleDao keyRoleDao;
    @Autowired
    RedisService redisService;

    final Integer UN_USE = 0;
    final Integer USED = 1;
    final Integer INVALID = 100;


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

    @Override
    public Response validate(String cdkey) {

        // 验证
        logger.info("=====>开始验证key " + cdkey);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cdkey", cdkey);
        CdKey cdKey = getOne(queryWrapper);
        if (cdKey == null) {
            return Response.error(INVALID, "无效key");
        }
        // 启动容器
        // docker run memory/wechat
        // docker run --env REDIS_HOST=140.143.226.139 --env REDIS_PORT=6379 --env REDIS_AUTH=2019#docker --env PROTOCOL_HOST=62.234.70.116 --env WEBSOCKET_PORT=22222 --env HTTP_PORT=222221 -d memory/wechat

        if (!Docker.getInstance().hasContainer(cdkey)) {
            logger.info("=====>开始启动容器 " + cdkey);
            createContainer(cdKey);
        } else {
            logger.info("=====>容器已经存在 " + cdkey);
        }
        logger.info("=====>更新key 状态 " + cdkey);
        if (cdKey.getFlag().equals(String.valueOf(UN_USE))) {
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("cdkey", cdkey);
            updateWrapper.set("flag", USED);
            update(updateWrapper);
        }
        return Response.success().put(cdKey);
    }

    @Async("taskExecutor")
    public void createContainer(CdKey cdKey) {
        String cdkey = cdKey.getCdkey();
        String image = "memory/wechat";
        String[] envs = new String[]{"CDKEY=" + cdkey, "REDIS_HOST=140.143.226.139", "REDIS_PORT=6379", "REDIS_AUTH=2019#docker",
                "PROTOCOL_HOST=62.234.70.116", "WEBSOCKET_PORT=22222", "HTTP_PORT=22221"};
        String containId = Docker.getInstance().Run(new MyContainer(cdkey, image, envs));
        redisService.hmSet("containId", cdKey, containId);
        logger.info("=====>容器启动完成 " + cdkey);
    }
}
