package com.sanqing.dao;

import com.sanqing.entity.SmsMessage;

import java.util.List;

public interface SmsMessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(SmsMessage smsMessage);

    int insertSelective(SmsMessage record);

    SmsMessage selectByPrimaryKey(Integer messageId);

    List<SmsMessage> selectAllMessageByMobile(String mobile);

    int updateByPrimaryKeySelective(SmsMessage record);

    int updateByPrimaryKey(SmsMessage record);
}