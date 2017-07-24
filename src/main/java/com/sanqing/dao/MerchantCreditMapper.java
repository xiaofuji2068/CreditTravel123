package com.sanqing.dao;

import com.sanqing.entity.MerchantCredit;

public interface MerchantCreditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MerchantCredit record);

    int insertSelective(MerchantCredit record);

    MerchantCredit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantCredit record);

    int updateByPrimaryKey(MerchantCredit record);
}