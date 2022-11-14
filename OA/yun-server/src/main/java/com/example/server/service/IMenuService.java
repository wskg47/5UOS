package com.example.server.service;

import com.example.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户id查询菜单列表
     * @return
     * */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     * */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return
     * */
    List<Menu> getAllMenus();
}
