package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.server.pojo.Employee;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.RespPageBean;
import com.example.server.pojo.Salary;
import com.example.server.service.IEmployeeService;
import com.example.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wdd
 * @date: 2022/10/13 10:53
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获得所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获得所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PostMapping("/")
    public RespBean updateEmployeeSalary(Integer eid,Integer sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("删除员工");
    }

}
