package com.gmadmin.mapper;

import com.gmadmin.bean.Monster;
import com.gmadmin.bean.MonsterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonsterMapper {
    int countByExample(MonsterExample example);

    int deleteByExample(MonsterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Monster record);

    int insertSelective(Monster record);

    List<Monster> selectByExample(MonsterExample example);

    Monster selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Monster record, @Param("example") MonsterExample example);

    int updateByExample(@Param("record") Monster record, @Param("example") MonsterExample example);

    int updateByPrimaryKeySelective(Monster record);

    int updateByPrimaryKey(Monster record);
}