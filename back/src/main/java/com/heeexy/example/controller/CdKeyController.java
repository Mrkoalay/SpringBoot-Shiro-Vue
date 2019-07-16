package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.entity.CdKey;
import com.heeexy.example.service.CdKeyService;
import com.heeexy.example.service.KeyRoleService;
import com.heeexy.example.service.RedisService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.QRUtil;
import com.heeexy.example.util.Response;
import com.heeexy.example.util.page.PageParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    KeyRoleService keyRoleService;


    @RequiresPermissions("cdkey:list")
    @GetMapping("/list")
    public Response listUser(PageParam pageParam, CdKey cdKey) {
        return cdKeyService.myList(pageParam,cdKey);
    }

    @PostMapping("/add")
    public Response addUser(@RequestBody(required = false) CdKey cdKey) {
        CommonUtil.hasAllRequired((JSONObject) JSONObject.toJSON(cdKey), "roleId");
        int count = cdKey.getCount();
        List<CdKey> cdKeyList = new ArrayList<>();
        while(count-- >0){
            CdKey tmpCdKey = new CdKey();
            tmpCdKey.setCdkey(UUID.randomUUID().toString());
            tmpCdKey.setRoleId(cdKey.getRoleId());
            tmpCdKey.setRemark(cdKey.getRemark());
            cdKeyList.add(tmpCdKey);
        }
        cdKeyService.saveBatch(cdKeyList);
    /*    MyContainer containd = new MyContainer(cdKey.getId() + "", cdKey.getCdkey());
        Docker.getInstance().Run(containd);*/
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
