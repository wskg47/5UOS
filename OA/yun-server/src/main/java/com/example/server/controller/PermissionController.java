package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.pojo.Menu;
import com.example.server.pojo.MenuRole;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IMenuRoleService;
import com.example.server.service.IMenuService;
import com.example.server.service.IRoleService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiModelProperty(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }
    @ApiModelProperty(value = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        //用来判断传入的Name前面是否有ROLE,springSecurity定义的格式，如果没有，就加上这个格式。
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
    @ApiModelProperty(value = "更新角色")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role){
        if (roleService.updateById(role)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
    @ApiModelProperty(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
    @ApiModelProperty(value = "查询所有菜单")
    @GetMapping("menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiModelProperty(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid))
                .stream()
                .map(MenuRole::getMid)
                .collect(Collectors.toList());
    }

    @ApiModelProperty(value = "更新角色菜单")
    @PutMapping()
    public RespBean updateRoleMenu(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
