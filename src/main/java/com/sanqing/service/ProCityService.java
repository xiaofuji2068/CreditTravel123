package com.sanqing.service;


import com.sanqing.dao.AreasMapper;
import com.sanqing.dao.CitiesMapper;
import com.sanqing.dao.ProvincesMapper;
import com.sanqing.entity.Areas;
import com.sanqing.entity.Cities;
import com.sanqing.entity.Provinces;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/6/28.
 */
public class ProCityService {
    public static ProCityService service = new ProCityService();
    private static final ClassPathXmlApplicationContext applicationContext;
    private static final ProvincesMapper provinceMapper;
    private static final CitiesMapper cityMapper;
    private static final AreasMapper areasMapper;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");//加载spring配置文件
        provinceMapper = applicationContext.getBean(ProvincesMapper.class);//在这里导入要测试的
        cityMapper = applicationContext.getBean(CitiesMapper.class);
        areasMapper = applicationContext.getBean(AreasMapper.class);
    }

    //查询省份list
    public List<Provinces> selectAllProvince() {
        List<Provinces> list = provinceMapper.selectAll();
        return list;
    }

    //根据省份查询城市
    public List<Cities> selectCityByProvince(String provinceId) {
        List<Cities> list = cityMapper.selectCityByProId(provinceId);
        return list;
    }

    //根据市查询县
    public List<Areas> selectAreasByCity(String cityid) {
        List<Areas> list = areasMapper.selectAreasByCity(cityid);
        return list;
    }

}
