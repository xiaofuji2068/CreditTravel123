package com.sanqing.dao;

import com.sanqing.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUserName(String userName);

    List<User> selectByMobile(String mobile);

    int updateByPrimaryKeySelective(User record);

    int updateByMobileSelective(User record);

    int updateByPrimaryKey(User record);

}