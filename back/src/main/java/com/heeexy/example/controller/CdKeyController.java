package com.heeexy.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.entity.MyContainer;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.Docker;
import com.heeexy.example.util.QRUtil;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageFactory;
import com.heeexy.example.util.page.PageParam;
import com.heeexy.example.util.page.PageResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @date: 2017/10/24 16:04
 */
@RestController
@RequestMapping("/cdkey")
public class CdKeyController {

    @Autowired
    private CdKeyService cdKeyService;
    @Autowired
    private RedisService redisService;


    @RequiresPermissions("cdkey:list")
    @GetMapping("/list")
    public Response listUser(PageParam pageParam, CdKey cdKey) {
        Page page = new PageFactory().buildPage(pageParam);
        Page result = (Page) cdKeyService.page(page, new QueryWrapper<>(cdKey));
        return Response.success().put(new PageResponse(result));
    }

    @PostMapping("/add")
    public Response addUser(@RequestBody(required = false) CdKey cdKey) {
        if (StringUtils.isEmpty(cdKey.getCdkey())) {
            cdKey.setCdkey(UUID.randomUUID().toString());
        }
        cdKeyService.save(cdKey);
        MyContainer containd = new MyContainer(cdKey.getId() + "", cdKey.getCdkey());
        Docker.getInstance().Run(containd);
        return Response.success();
    }

    @RequiresPermissions("cdkey:update")
    @PostMapping("/update")
    public Response update(@RequestBody CdKey cdkey) {
        cdKeyService.updateById(cdkey);
        return Response.success();
    }

    @RequiresPermissions("cdkey:update")
    @PostMapping("/delete")
    public Response delete(@RequestBody CdKey cdkey) {
        cdKeyService.removeById(cdkey.getId());
        return Response.success();
    }

    @RequiresPermissions("cdkey:update")
    @GetMapping("/qrcode")
    public void qrcode(HttpServletResponse response, Integer id) throws Exception {

        BufferedImage qrcode = QRUtil.getRQ((String) redisService.hmGet("qrcode", id + ""), 100);

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(qrcode, "png", os);
    }


}
