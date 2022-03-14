package com.duyi.examonline.test;

import com.duyi.examonline.common.CommonData;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * 字符串分隔符测试
     */
    @Test
    public void t3() throws IOException {
        String str = "abc--\r\nabc--\r\nabc--\r\n" ;
        FileWriter w = new FileWriter("f:/z/2.txt");
        w.write(str);
        w.close();
        String[] array = str.split("--\r\n");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 指定分割上限
     */
    @Test
    public void t4(){
        String s1 = "a," ;
        System.out.println(Arrays.toString(s1.split(",")));
        System.out.println(Arrays.toString(s1.split(",",2)));
        System.out.println(s1.split(",",2).length);
    }


}
