package com.gmadmin.mapper;

import com.gmadmin.bean.RoleCareer;
import com.gmadmin.bean.RoleCareerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleCareerMapper {
    int countByExample(RoleCareerExample example);

    int deleteByExample(RoleCareerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleCareer record);

    int insertSelective(RoleCareer record);

    List<RoleCareer> selectByExample(RoleCareerExample example);

    RoleCareer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleCareer record, @Param("example") RoleCareerExample example);

    int updateByExample(@Param("record") RoleCareer record, @Param("example") RoleCareerExample example);

    int updateByPrimaryKeySelective(RoleCareer record);

    int updateByPrimaryKey(RoleCareer record);
}