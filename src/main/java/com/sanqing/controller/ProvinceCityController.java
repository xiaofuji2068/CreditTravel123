package com.sanqing.controller;

import com.sanqing.entity.Areas;
import com.sanqing.entity.Cities;
import com.sanqing.entity.JsonResult;
import com.sanqing.entity.Provinces;
import com.sanqing.service.ProCityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by admin on 2017/7/17.
 */
@Controller
public class ProvinceCityController {
    @RequestMapping(value = "proAndCity/selectAllProvince", method = POST)
    public @ResponseBody JsonResult selectProvinceName(){
        JsonResult jsonResult = null;
        List<Provinces> list = ProCityService.service.selectAllProvince();
        if (null != list && list.size() > 0) {
            jsonResult = new JsonResult(list, "查询成功", "0");
            return jsonResult;
        }
        jsonResult = new JsonResult("", "查询失败","1");
        return jsonResult;
    }

    @RequestMapping(value = "proAndCity/selectCityByProid", method = POST)
    public @ResponseBody JsonResult selectCityByProid(@RequestParam("porvinceId") String porvinceId ){
        JsonResult jsonResult = null;
        List<Cities> list = ProCityService.service.selectCityByProvince(porvinceId);
        if (null != list && list.size() > 0) {
            jsonResult = new JsonResult(list, "查询成功", "0");
            return jsonResult;
        }
        jsonResult = new JsonResult("", "查询失败","1");
        return jsonResult;
    }

    @RequestMapping(value = "proAndCity/selectAreaByCityid", method = POST)
    public @ResponseBody JsonResult selectAreaByCityid(@RequestParam("cityId") String cityId ){
        JsonResult jsonResult = null;
        List<Areas> list = ProCityService.service.selectAreasByCity(cityId);
        if (null != list && list.size() > 0) {
            jsonResult = new JsonResult(list, "查询成功", "0");
            return jsonResult;
        }
        jsonResult = new JsonResult("", "查询失败","1");
        return jsonResult;
    }

}
