package com.gmadmin.mapper;

import com.gmadmin.bean.MonsterType;
import com.gmadmin.bean.MonsterTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonsterTypeMapper {
    int countByExample(MonsterTypeExample example);

    int deleteByExample(MonsterTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonsterType record);

    int insertSelective(MonsterType record);

    List<MonsterType> selectByExample(MonsterTypeExample example);

    MonsterType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonsterType record, @Param("example") MonsterTypeExample example);

    int updateByExample(@Param("record") MonsterType record, @Param("example") MonsterTypeExample example);

    int updateByPrimaryKeySelective(MonsterType record);

    int updateByPrimaryKey(MonsterType record);
}