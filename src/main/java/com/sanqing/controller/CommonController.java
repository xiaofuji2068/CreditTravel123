package com.sanqing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/7/5.
 */
@Controller
public class CommonController {
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

    @RequestMapping("testSession")
    public String testSession(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("session", session);
        return "testSession";
    }
}
