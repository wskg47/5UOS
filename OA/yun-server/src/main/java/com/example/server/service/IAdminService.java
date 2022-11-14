package com.example.server.service;

import com.example.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Menu;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param code
     * @param request  */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * */
    List<Role> getRoles(Integer adminId);

    /**
     * 获得所有操作员
     * */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     * */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新用户密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}
