package com.sanqing.dao;

import com.sanqing.entity.MerchantDo;

public interface MerchantDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MerchantDo record);

    int insertSelective(MerchantDo record);

    MerchantDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantDo record);

    int updateByPrimaryKey(MerchantDo record);
}