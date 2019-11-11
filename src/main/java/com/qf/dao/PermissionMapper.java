package com.qf.dao;

import com.qf.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer perId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    Permission selectByPrimaryKey(Integer perId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissionByStaffNumber(String staffNumber);
}