package com.example.server.service.impl;

import com.example.server.utils.AdminUtils;
import com.example.server.pojo.Menu;
import com.example.server.mapper.MenuMapper;
import com.example.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户id查询菜单
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        // 获得登录的AdminId
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
//         Redis
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//         从Redis获取菜单
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
//         如果为空，去数据库获取
        if (CollectionUtils.isEmpty(menus)){
            menus=menuMapper.getMenusByAdminId(adminId);
            // 将查询的菜单放到Redis中
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    /**
     * 根据角色获取列表
     * @return
     */

    @Override
    public List<Menu> getMenusWithRole() {
        return  menuMapper.getMenusWithRole();
    }


    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
