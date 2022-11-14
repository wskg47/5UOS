package com.example.server.mapper;

import com.example.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     */

    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     * @return
     */
    void addDepartment(Department department);

    /**
     * 删除部门
     * @return
     */
    void deleteDepartment(Department department);
}
