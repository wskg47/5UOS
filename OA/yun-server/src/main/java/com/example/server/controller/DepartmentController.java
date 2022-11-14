package com.example.server.controller;


import com.example.server.pojo.Department;
import com.example.server.pojo.RespBean;
import com.example.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    @GetMapping("/")
    @ApiOperation(value = "获取所有部门")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }


    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/")
    public RespBean deleteDepartment(@RequestBody Integer id){
        return departmentService.deleteDepartmentById(id);
    }
}
