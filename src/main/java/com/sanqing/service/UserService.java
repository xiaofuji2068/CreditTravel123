package com.sanqing.service;


import com.sanqing.dao.UserMapper;
import com.sanqing.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/6/28.
 */
public class UserService {
    public static UserService service = new UserService();
    private static final ClassPathXmlApplicationContext applicationContext;
    private static final UserMapper userMapper;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//加载spring配置文件
        userMapper = applicationContext.getBean(UserMapper.class);//在这里导入要测试的
    }

    //用户注册
    public boolean registerUser(User user) {
        userMapper.insert(user);
        return false;
    }

    //查询用户登陆
    public List<User> selectUserByMobile(String  mobile) {
       List<User> user = userMapper.selectByMobile(mobile);
        return user;
    }

    public User selectByPrimaryKey(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    public int insertUser(User user) {
        int flag = userMapper.insert(user);
        return flag;
    }

    public int updateByPrimaryKeySelective(User user) {
        int flag = userMapper.updateByPrimaryKeySelective(user);
        return flag;
    }

    //上传用户头像
    public int insterUserPhoto(User user,String path) {
        user.setPhoto(path);
        int flag = userMapper.updateByPrimaryKeySelective(user);
        return flag;
    }
}
