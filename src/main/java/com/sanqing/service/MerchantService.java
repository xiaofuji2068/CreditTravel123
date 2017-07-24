package com.sanqing.service;

import com.sanqing.dao.MerchantMapper;
import com.sanqing.entity.Merchant;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/7/12.
 */
public class MerchantService {

    public static MerchantService service = new MerchantService();
    private static final ClassPathXmlApplicationContext applicationContext;
    private static final MerchantMapper merchantMapper;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//加载spring配置文件
        merchantMapper = applicationContext.getBean(MerchantMapper.class);//在这里导入要测试的
    }
    //查询商户信息
    public List<Merchant> selectMerchantByName(String merName) {
        List<Merchant> merchants = merchantMapper.selectByName(merName);
        return merchants;
    }
}
