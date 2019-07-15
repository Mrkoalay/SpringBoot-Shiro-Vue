package com.heeexy.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.Docker;
import com.heeexy.example.util.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2017/10/24 16:04
 */
@Api(description = "业务接口相关")
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private CdKeyService cdKeyService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final int UNFIND = 99;

    @ApiResponses(value = {
            @ApiResponse(code = 100, message = "无效key")
    })
    @ApiOperation(value = "校验cdkey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @PostMapping("/validate")
    public Response validate(@RequestParam("cdkey") String cdkey) {
        return cdKeyService.validate(cdkey);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 99, message = "未查找到二维码信息")
    })
    @ApiOperation(value = "获取二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @GetMapping("/qrcode")
    public Response qrcode(@RequestParam("cdkey") String cdkey) {
        String qrCode = stringRedisTemplate.opsForValue().get("qrcode_" + cdkey);
        if (StringUtils.isEmpty(qrCode)) {
            return Response.error(UNFIND, "未查找到二维码信息");
        }
        return Response.success().put(qrCode);
    }

    @ApiOperation(value = "获取状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "String", paramType = "query"),
    })
    @GetMapping("/status")
    public Response status(@RequestParam("cdkey") String cdkey) {
        String status = stringRedisTemplate.opsForValue().get("status_" + cdkey);
        JSONObject data = new JSONObject();
        if (StringUtils.isEmpty(status)) {
            data.put("status", -999);
            return Response.success().put(data);
        }
        data = JSON.parseObject(status);
        return Response.success().put(data);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 99, message = "未查找到相关信息")
    })
    @ApiOperation(value = "任务结束")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @PostMapping("/finish")
    public Response finish(@RequestParam("cdkey") String cdkey) {
        String containId = (String) redisService.hmGet("containId", cdkey);
        if (StringUtils.isEmpty(containId)) {
            return Response.error(UNFIND, "未查找到相关信息");
        }
        Docker.getInstance().removeContainer(containId) ;
        return Response.success();
    }
}
