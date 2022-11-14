package com.example.server.controller;


import com.example.server.pojo.Admin;
import com.example.server.pojo.AdminLoginParam;
import com.example.server.pojo.RespBean;
import com.example.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    /**
     * 获取当前登录用户的信息
     *
     * @param principal
     * @return
     */
    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    //在运行过程中，Spring 会将 Username、Password、Authentication、Token 注入到 Principal 接口中，我们可以直接获取使用
    public Admin getAdminInfo(Principal principal) {
        // 因为我们把当前登录对象设置到了Spring Security的全局中，所以这里可以通过Principal直接获取
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
//        // 通过用户名username获取一个完整的用户对象
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null); // 因为用户密码不能被返回，所以要设置为null
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功！");
    }
}