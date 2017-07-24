package com.sanqing.dao;

import com.sanqing.entity.CommentLabel;

public interface CommentLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLabel record);

    int insertSelective(CommentLabel record);

    CommentLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLabel record);

    int updateByPrimaryKey(CommentLabel record);
}