package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获得所有操作员
     * @param id
     * @param keywords
     * @return
     */
    List<Admin>  getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

}

