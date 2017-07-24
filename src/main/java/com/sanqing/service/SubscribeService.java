package com.sanqing.service;

import com.sanqing.dao.SubscribeMapper;
import com.sanqing.entity.Subscribe;
import com.sanqing.kit.SpringContextUtil;
import org.springframework.dao.DataAccessException;

/**
 * Created by admin on 2017/7/19.
 */
public class SubscribeService {

    public static SubscribeService service = new SubscribeService();
    SubscribeMapper subscribeMapper = SpringContextUtil.getBeanByName(SubscribeMapper.class);

    public int insertSelective(Subscribe order) throws DataAccessException {
        return subscribeMapper.insertSelective(order);
    }

    public Subscribe selectByPrimaryKey(int orderId)  throws DataAccessException {
        return subscribeMapper.selectByPrimaryKey(orderId);
    }

    public void updateByPrimaryKeySelective(Subscribe subscribe) throws DataAccessException {
        subscribeMapper.updateByPrimaryKeySelective(subscribe);
    }
}
