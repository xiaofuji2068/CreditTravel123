package com.sanqing.service;

import com.sanqing.dao.CommentLabelMapper;
import com.sanqing.dao.ComplaintLabelMapper;
import com.sanqing.dao.ServiceItemsMapper;
import com.sanqing.entity.CommentLabel;
import com.sanqing.entity.ComplaintLabel;
import com.sanqing.entity.ServiceItems;
import com.sanqing.kit.SpringContextUtil;
import org.springframework.dao.DataAccessException;

public class LabelService {
    public static LabelService service = new LabelService();
    CommentLabelMapper commentLabelMapper = SpringContextUtil.getBeanByName(CommentLabelMapper.class);
    ComplaintLabelMapper complaintLabelMapper = SpringContextUtil.getBeanByName(ComplaintLabelMapper.class);
    ServiceItemsMapper serviceItemsMapper = SpringContextUtil.getBeanByName(ServiceItemsMapper.class);

    //查询评论标签
    public CommentLabel queryCommentLabel(int id) throws DataAccessException {
        return commentLabelMapper.selectByPrimaryKey(id);
    }

    //查询投诉标签
    public ComplaintLabel queryCompliantLabel(int id) throws DataAccessException {
        return complaintLabelMapper.selectByPrimaryKey(id);
    }

    //查询服务项目
    public ServiceItems queryServiceItems(int id) throws DataAccessException {
        return serviceItemsMapper.selectByPrimaryKey(id);
    }
}
