package com.sanqing.controller;

import com.sanqing.entity.JsonResult;
import com.sanqing.entity.Merchant;
import com.sanqing.service.MerchantService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/7/12.
 */
@Controller
public class MerchantController {
    @RequestMapping(value = "merchant/queryMerchant", method = RequestMethod.POST)
    public @ResponseBody JsonResult<List<Merchant>> queryMerchant(@RequestParam("merName") String merName){
//        String merName = request.getParameter("merName");
        System.out.println("HttpServletRequest获取请求参数merName:" + merName);
        List<Merchant> list = MerchantService.service.selectMerchantByName(merName);

        JsonResult<List<Merchant>> jsonResult = new JsonResult(list, "查询成功", "1");

        JSONObject jsonObject = new JSONObject();

        return  jsonResult;
    }
}

