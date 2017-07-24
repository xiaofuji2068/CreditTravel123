package com.sanqing.service;

import com.sanqing.controller.UserController;
import com.sanqing.dao.SmsMessageMapper;
import com.sanqing.entity.SmsMessage;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/7/6.
 */
public class CountService {
    public final static CountService service = new CountService();

    private static Logger log = Logger.getLogger(UserController.class);

    private static final ClassPathXmlApplicationContext applicationContext;

    private static final SmsMessageMapper smsMessageMapper;

    static{
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//加载spring配置文件
        smsMessageMapper = applicationContext.getBean(SmsMessageMapper.class);//在这里导入要测试的
    }

    public CountService() {

    }

    public List<SmsMessage> findAllRecord(String mobile) {
        List<SmsMessage> list = smsMessageMapper.selectAllMessageByMobile(mobile);
        return list;
    }

    public void saveEntity(SmsMessage smsMessage) {
        smsMessageMapper.insert(smsMessage);
    }
}
