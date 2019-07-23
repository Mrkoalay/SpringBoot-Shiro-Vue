package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.KeyRoleService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/keyRole")
@RestController
public class KeyRoleController {
    @Autowired
    KeyRoleService keyRoleService;

    @GetMapping("/getAllRoles")
    public JSONObject getAllRoles() {
        return keyRoleService.getAllRoles();
    }

    /**
     * 角色列表
     *
     * @return
     */
    @GetMapping("/listRole")
    public JSONObject listRole() {
        return keyRoleService.listRole();
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     *
     * @return
     */
    @GetMapping("/listAllPermission")
    public JSONObject listAllPermission() {
        return keyRoleService.listAllPermission();
    }

    /**
     * 新增角色
     *
     * @return
     */
    @PostMapping("/addRole")
    public JSONObject addRole(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleName,permissions");
        return keyRoleService.addRole(requestJson);
    }

    /**
     * 修改角色
     *
     * @return
     */
    @PostMapping("/updateRole")
    public JSONObject updateRole(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");
        return keyRoleService.updateRole(requestJson);
    }

    /**
     * 删除角色
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/deleteRole")
    public JSONObject deleteRole(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleId");
        return keyRoleService.deleteRole(requestJson);
    }
}
