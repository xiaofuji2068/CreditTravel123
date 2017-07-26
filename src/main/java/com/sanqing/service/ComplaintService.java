package com.sanqing.service;

import com.sanqing.dao.ComplaintMapper;
import com.sanqing.entity.Complaint;
import com.sanqing.kit.SpringContextUtil;
import org.springframework.dao.DataAccessException;

/**
 * Created by admin on 2017/7/19.
 */
public class ComplaintService {

    public static ComplaintService service = new ComplaintService();
    ComplaintMapper complaintMapper = SpringContextUtil.getBeanByName(ComplaintMapper.class);

    public int insertSelective(Complaint complaint) throws DataAccessException {
        return complaintMapper.insertSelective(complaint);
    }

    public Complaint queryComplaint(int id) throws DataAccessException {
        return complaintMapper.selectByPrimaryKey(id);
    }
}