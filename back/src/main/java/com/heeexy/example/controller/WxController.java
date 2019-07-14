package com.heeexy.example.controller;

import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2017/10/24 16:04
 */
@Api(description = "业务接口相关")
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private CdKeyService cdKeyService;


    @ApiOperation(value = "校验cdkey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cdkey", value = "cdkey", required = true, dataType = "Integer", paramType = "query"),
    })
    @PostMapping("/validate")
    public Response validate(@RequestParam("cdkey")String cdkey) {
        return  cdKeyService.validate(cdkey);
    }

}
