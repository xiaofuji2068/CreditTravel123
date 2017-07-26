package com.sanqing.controller;

import com.sanqing.entity.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/5.
 */
@Controller
public class CommonController {
    private String FileName;

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("testSaveEntity")
    public String testSaveEntity() {
        return "testSaveEntity";
    }

    @RequestMapping(value = "testSession",method = RequestMethod.POST)
    public @ResponseBody
    JsonResult testSession(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        JsonResult jsonResult = null;
        Map map = new HashMap();
        map.put("session", session);

        Enumeration<String> enumeration = session.getAttributeNames();
        while(enumeration.hasMoreElements()){
            String AddFileName=enumeration.nextElement().toString();//获取session中的键值
            String value=(String)session.getAttribute(AddFileName);//根据键值取出session中的值
            System.out.println(value);
            FileName+=value+"@";
            System.out.println(FileName);
            //String FileName= (String)session.getAttribute("AddFileName");
        }

        jsonResult = new JsonResult("", "", "");

        model.addAttribute("session", session);
        return jsonResult;
    }
}
