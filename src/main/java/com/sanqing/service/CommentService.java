package com.sanqing.service;

import com.sanqing.dao.CommentMapper;
import com.sanqing.entity.Comment;
import com.sanqing.kit.SpringContextUtil;
import org.springframework.dao.DataAccessException;

/**
 * Created by admin on 2017/7/24.
 */
public class CommentService {
    public static CommentService service = new CommentService();
    CommentMapper commentMapper = SpringContextUtil.getBeanByName(CommentMapper.class);

    public int insert(Comment comment) throws DataAccessException {
        return commentMapper.insert(comment);
    }

    public int insertSelective(Comment comment) throws DataAccessException {
        return commentMapper.insertSelective(comment);
    }

    public Comment queryComment(int id) throws DataAccessException {
        return commentMapper.selectByPrimaryKey(id);
    }

    public Comment queryCommentByUserId(int id) throws DataAccessException {
        return commentMapper.selectCommentByUserId(id);
    }

    public Comment queryCommentByMerchantId(int id) throws DataAccessException {
        return commentMapper.selectCommentByMerchantId(id);
    }

}
