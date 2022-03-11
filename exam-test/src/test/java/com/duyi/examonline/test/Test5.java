package com.duyi.examonline.test;

import com.duyi.examonline.domain.StudentExam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池等待测试
 */
public class Test5 {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);
        //要做100个处理，但只是用10个线程，每次只会执行10个，其他会处于等待状态（等待队列）
        //其他操作再等待队列中等待时，是否会影响主线程呢
        for(int i=0;i<100;i++){
            final int j = i ;
            pool.execute(()->{
                System.out.println(j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        System.out.println("---------主线程执行--------");



    }
}
