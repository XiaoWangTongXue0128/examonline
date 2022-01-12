package com.test.controller;

import com.test.domain.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class TestController {

    @RequestMapping("/test1")
    public String test1(Model model, HttpSession session){

        model.addAttribute("name","<b>dmc</b>") ;
        model.addAttribute("age",18);
        model.addAttribute("names",new String[]{"dmc1","dmc2","dmc3","dmc4"});

        model.addAttribute("car",new Car(1L,"bmw","blue",300000.0));

        model.addAttribute("today",new Date());

        session.setAttribute("username","zzt");

        //  /WEB-INF/ty/ 1 .html
        return "1" ;
    }

}
