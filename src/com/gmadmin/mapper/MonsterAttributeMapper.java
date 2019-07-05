package com.gmadmin.mapper;

import com.gmadmin.bean.MonsterAttribute;
import com.gmadmin.bean.MonsterAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonsterAttributeMapper {
    int countByExample(MonsterAttributeExample example);

    int deleteByExample(MonsterAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonsterAttribute record);

    int insertSelective(MonsterAttribute record);

    List<MonsterAttribute> selectByExample(MonsterAttributeExample example);

    MonsterAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonsterAttribute record, @Param("example") MonsterAttributeExample example);

    int updateByExample(@Param("record") MonsterAttribute record, @Param("example") MonsterAttributeExample example);

    int updateByPrimaryKeySelective(MonsterAttribute record);

    int updateByPrimaryKey(MonsterAttribute record);
}