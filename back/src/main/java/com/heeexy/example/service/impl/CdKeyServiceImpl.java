package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dockerjava.api.model.Container;
import com.heeexy.example.dao.CdKeyDao;
import com.heeexy.example.dao.KeyRoleDao;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.entity.MyContainer;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.DockerService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.Docker;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageFactory;
import com.heeexy.example.util.page.PageParam;
import com.heeexy.example.util.page.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    DockerService dockerService;

    @Autowired
    Docker docker;

    final Integer UN_USE = 0;
    final Integer USED = 1;
    final Integer INVALID = 100;
    final Integer FINISH = 2;
    final Integer PROCESS = 3;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


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
        } else if (cdKey.getFlag().equals(String.valueOf(FINISH))) {
            return Response.error(FINISH, "已经使用");
        }
        // 启动容器
        // docker run memory/wechat
        // docker run --env REDIS_HOST=140.143.226.139 --env REDIS_PORT=6379 --env REDIS_AUTH=2019#docker --env PROTOCOL_HOST=62.234.70.116 --env WEBSOCKET_PORT=22222 --env HTTP_PORT=222221 -d memory/wechat
        Container container = docker.getContainerByNames(cdkey);
        if (container != null && docker.isRunning(container)) {
            return Response.error(PROCESS, "当前激活码正在使用中，请在微信中操作");
        }

        //清空原始状态
        stringRedisTemplate.delete("status_" + cdkey);
        stringRedisTemplate.delete("face_" + cdkey);
        stringRedisTemplate.delete("qrcode_" + cdkey);

        dockerService.runContainer(cdKey);

        if (cdKey.getFlag().equals(String.valueOf(UN_USE))) {
            logger.info("=====>更新key 状态 " + cdkey);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("cdkey", cdkey);
            updateWrapper.set("flag", USED);
            update(updateWrapper);
        }

        return Response.success().put(cdKey);
    }


}
