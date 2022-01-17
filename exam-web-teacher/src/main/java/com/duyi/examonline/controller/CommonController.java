package com.duyi.examonline.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 提供一些基本功能处理
 */
@Controller
public class CommonController {

    Logger log = LoggerFactory.getLogger(CommonController.class) ;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping({"/common/login.html","/"})
    public String toLogin(){
        return "common/login" ;
    }


    @RequestMapping("/common/login")
    @ResponseBody
    public boolean checkLogin(String tname , String pass, HttpSession session){
        log.info(tname);
        log.info(pass);

        //根据用户名找用户信息
        Teacher teacher = teacherService.findByName(tname);
        if(teacher == null){
            log.info("登录失败-用户名不正确");
            return false ;
        }

        //再判断密码
        pass = DigestUtil.md5Hex(pass) ;
        if(!teacher.getPass().equals(pass)){
            log.info("登录失败-密码错误");
            return false ;
        }

        log.info("登录成功");
        session.setAttribute("loginTeacher",teacher);
        return true ;
    }

    @RequestMapping("/common/main.html")
    public String toMain(){
        return "common/main" ;
    }

    @RequestMapping("/common/exit")
    public String exit(HttpSession session){
        //session.invalidate();
        session.removeAttribute("loginTeacher");
        return "common/login" ;
    }

    @RequestMapping("/common/timeout.html")
    public String toTimeout(){
        return "common/timeout" ;
    }

    @RequestMapping("/common/updatePwdTemplate.html")
    public String toUpdatePwd(){
        return "common/updatePwdTemplate" ;
    }

    @RequestMapping("/common/updatePwd")
    @ResponseBody
    public boolean updatePwd(String old_pass , String new_pass,HttpSession session){
        log.debug("old_pass ["+old_pass+"]");
        log.debug("new_pass [{}]",new_pass);

        Teacher teacher = (Teacher) session.getAttribute("loginTeacher");
        old_pass = DigestUtil.md5Hex(old_pass) ;
        if(!teacher.getPass().equals(old_pass)){
            log.debug("update fail");
            return false ;
        }

        new_pass = DigestUtil.md5Hex(new_pass);
        Long tid = teacher.getId();
        teacherService.updatePwd(tid,new_pass);

        log.debug("update success");
        return true ;
    }


}
