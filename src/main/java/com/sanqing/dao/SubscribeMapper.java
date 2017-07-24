package com.sanqing.dao;

import com.sanqing.entity.Subscribe;
import org.springframework.dao.DataAccessException;

public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Subscribe record);

    int insertSelective(Subscribe record)throws DataAccessException;

    Subscribe selectByPrimaryKey(Integer orderId)throws DataAccessException;

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}