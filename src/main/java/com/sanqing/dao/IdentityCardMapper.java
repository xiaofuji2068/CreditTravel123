package com.sanqing.dao;

import com.sanqing.entity.IdentityCard;

public interface IdentityCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentityCard record);

    int insertSelective(IdentityCard record);

    IdentityCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentityCard record);

    int updateByPrimaryKey(IdentityCard record);
}