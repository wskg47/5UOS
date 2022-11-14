package com.example.server.service;

import com.example.server.pojo.Department;
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
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @return
     */
    RespBean addDepartment(Department department);


    /**
     * 删除部门
     * @return
     */
    RespBean deleteDepartmentById(Integer id);
}
