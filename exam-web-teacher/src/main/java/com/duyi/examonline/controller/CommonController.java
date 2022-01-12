package com.duyi.examonline.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 提供一些基本功能处理
 */
@Controller
public class CommonController {

    @RequestMapping("/login.html")
    public String toLogin(){
        return "login" ;
    }

}
