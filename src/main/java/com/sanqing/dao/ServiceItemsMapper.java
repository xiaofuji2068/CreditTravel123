package com.sanqing.dao;

import com.sanqing.entity.ServiceItems;

public interface ServiceItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceItems record);

    int insertSelective(ServiceItems record);

    ServiceItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceItems record);

    int updateByPrimaryKey(ServiceItems record);
}