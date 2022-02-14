package com.duyi.examonline.test;

import com.duyi.examonline.common.CommonData;
import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;

/**
 * 零散测试
 */
public class Test3 {

    /**
     * 测试 }-|-{ 格式的分割
     */
    @Test
    public void t1(){
        String s = "a" + CommonData.SEPARATOR + "b" + CommonData.SEPARATOR + "c" ;
        //System.out.println(s);
        String[] ss = s.split(CommonData.SPLIT_SEPARATOR);
        System.out.println(Arrays.toString(ss));
        Connection conn = null ;
    }

    /**
     * 字符串数组的排序
     */
    @Test
    public void t2(){
        String[] indexArray = {"5","1","4","2"};
        System.out.println(Arrays.toString(indexArray));
        Arrays.sort(indexArray);
        System.out.println(Arrays.toString(indexArray));
    }

}
