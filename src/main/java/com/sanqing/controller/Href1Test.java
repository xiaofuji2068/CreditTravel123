package com.sanqing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;


@Controller
public class Href1Test {
    @RequestMapping("test/href1")
    public String testHref() {
        return "firstTest";
    }

    @RequestMapping("test/href2")
    public String test2(Model model) {
        Random random = new Random();
        model.addAttribute("data", random.nextInt(100));
        return "test2";
    }

    @RequestMapping("test/href3/data={chuan}")
    public String test3(@PathVariable("chuan") String data, Model model) {

        /*Random random = new Random();*/
        model.addAttribute("data", data);

        return "test2";
    }

    @RequestMapping(value = "test/href4", method = RequestMethod.GET)
    public String test4(@RequestParam("t1") int begin, @RequestParam("t2") int end, Model model) {

        Random random = new Random();
        model.addAttribute("data", begin + random.nextInt(end - begin));
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        return "test2";
    }

    @RequestMapping(value = "test/href4", method = RequestMethod.POST)
    public String test5(@RequestParam("t1") int begin, @RequestParam("t2") int end, Model model) {

        Random random = new Random();
        model.addAttribute("data", begin + random.nextInt(end - begin));
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "test3";


    }
}

