package com.gmadmin.mapper;

import com.gmadmin.bean.RoleGoods;
import com.gmadmin.bean.RoleGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleGoodsMapper {
    int countByExample(RoleGoodsExample example);

    int deleteByExample(RoleGoodsExample example);

    int insert(RoleGoods record);

    int insertSelective(RoleGoods record);

    List<RoleGoods> selectByExample(RoleGoodsExample example);

    int updateByExampleSelective(@Param("record") RoleGoods record, @Param("example") RoleGoodsExample example);

    int updateByExample(@Param("record") RoleGoods record, @Param("example") RoleGoodsExample example);
}