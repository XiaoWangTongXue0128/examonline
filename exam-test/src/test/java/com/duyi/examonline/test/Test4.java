package com.duyi.examonline.test;

import org.junit.Test;

public class Test4 {

    /**
     * 测试空内容的字符串分割 ",,,"
     */
    @Test
    public void t1(){
        String s = ",,," ;
        System.out.println( s.split(",").length);
    }

}
