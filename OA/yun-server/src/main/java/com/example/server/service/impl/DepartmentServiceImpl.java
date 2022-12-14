package com.example.server.service.impl;

import com.example.server.pojo.Department;
import com.example.server.mapper.DepartmentMapper;
import com.example.server.pojo.RespBean;
import com.example.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     */
    @Override
    public List<Department> getAllDepartments() {

        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @return
     */
    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (1 == department.getResult()) {
            return RespBean.success("添加成功!", department);
        }
        return RespBean.error("添加失败!");
    }

    /**
     * 删除部门
     * @return
     */
    @Override
    public RespBean deleteDepartmentById(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDepartment(department);
        if (-2 == department.getResult()) {
            return RespBean.error("该部门下还有子部门,删除失败!");
        }
        if (-1 == department.getResult()) {
            return RespBean.error("该部门下还有员工,删除失败!");
        }
        if (1 == department.getResult()) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
