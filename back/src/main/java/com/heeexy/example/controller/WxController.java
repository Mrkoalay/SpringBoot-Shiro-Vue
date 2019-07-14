package com.heeexy.example.controller;

import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

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


    @ApiOperation(value = "校验cdkey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @PostMapping("/validate")
    public Response validate(@RequestParam("cdkey") String cdkey) {
        return cdKeyService.validate(cdkey);
    }

    @ApiOperation(value = "获取二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @GetMapping("/qrcode")
    public Response qrcode(@RequestParam("cdkey") String cdkey) {

        return Response.success().put(redisService.get("qrcode_" + cdkey));
    }


}
