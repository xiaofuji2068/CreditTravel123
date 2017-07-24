package com.sanqing.dao;

import com.sanqing.entity.Cities;

import java.util.List;

public interface CitiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cities record);

    int insertSelective(Cities record);

    Cities selectByPrimaryKey(Integer id);

    List<Cities> selectCityByProId(String provinceId);

    int updateByPrimaryKeySelective(Cities record);

    int updateByPrimaryKey(Cities record);
}