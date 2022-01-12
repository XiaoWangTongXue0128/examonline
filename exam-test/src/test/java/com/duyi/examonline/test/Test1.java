package com.duyi.examonline.test;

import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
    初始化教师信息
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class Test1 {

    @Autowired
    private TeacherService teacherService ;

    @Test
    public void t1(){
        Teacher t = new Teacher();
        t.setTname("张三");
        t.setPass("123");

        teacherService.save(t);

    }




}
