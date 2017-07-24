package com.sanqing.dao;

import com.sanqing.entity.UserCredit;

public interface UserCreditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCredit record);

    int insertSelective(UserCredit record);

    UserCredit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCredit record);

    int updateByPrimaryKey(UserCredit record);
}