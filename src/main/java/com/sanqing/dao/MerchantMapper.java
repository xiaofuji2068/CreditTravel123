package com.sanqing.dao;

import com.sanqing.entity.Merchant;

import java.util.List;

public interface MerchantMapper {
    int deleteByPrimaryKey(Integer merId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Integer merId);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);

    List<Merchant> selectByName(String merName);
}