package com.sanqing.dao;

import com.sanqing.entity.ComplaintLabel;

public interface ComplaintLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ComplaintLabel record);

    int insertSelective(ComplaintLabel record);

    ComplaintLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ComplaintLabel record);

    int updateByPrimaryKey(ComplaintLabel record);
}