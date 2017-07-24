package com.sanqing.dao;

import com.sanqing.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by wanghuan on 2017/6/22.
 */
public class UserMapperTest {
    private ClassPathXmlApplicationContext applicationContext;
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//加载spring配置文件
        userMapper = applicationContext.getBean(UserMapper.class);//在这里导入要测试的
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        User user = new User();
//        user.setId(12);
        user.setNickname("三零");
//        user.setQuestion("出生地");
        int result = userMapper.insertSelective(user);
        System.out.println(result);

    }

}