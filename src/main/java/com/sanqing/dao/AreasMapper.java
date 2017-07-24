package com.sanqing.dao;

import com.sanqing.entity.Areas;

import java.util.List;

public interface AreasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Areas record);

    int insertSelective(Areas record);

    Areas selectByPrimaryKey(Integer id);

    List<Areas> selectAreasByCity(String cityid);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
}