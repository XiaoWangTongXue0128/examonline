package com.duyi.examonline.test;

import org.junit.Test;

/**
 * 蛇形数组（二维数组）
 * 1	2	6	7
 *
 * 3	5	8	13
 *
 * 4	9	12	14
 *
 * 10	11	15	16
 */
public class Test2 {

    public static void main(String[] args){
        int[][] nums = new int[4][4] ;

        final int r = 0 ; //右
        final int d = 1 ; //下
        final int ld = 2 ;//左下
        final int ru = 3 ;//右上
        int i = 0 ;
        int j = 0 ;
        int dir = r ; //初始方向
        nums[i][j] = 1 ;//初始第一个数

        //循环，根据方向找到每一个数存放的位置
        for(int num=2;num<=16;num++){
            if(dir == r){
                //方向向右，j++ ;
                j++ ;
                //考虑接下来的方向
                if(i==0){
                    //最上面向右，接下来就是左下
                    dir = ld ;
                }
                if(i == nums.length-1){
                    //最下面向右，接下来就是右上
                    dir = ru ;
                }
            }else if(dir == ld){
                i++ ;
                j-- ;
                //考虑接下来的方向
                if(j==0){
                    //到了左边界
                    if(i==nums.length-1){
                        //到了最下面（最下角），向右
                        dir = r ;
                    }else{
                        //向下
                        dir = d ;
                    }
                }else if(i==nums.length-1){
                    //到了下边界
                    dir = r ;
                }
            }else if(dir ==d ){
                i++ ;
                //考虑接下来的方向
                if(j==0){
                    //左边界向下，右上
                    dir = ru ;
                }else if(j==nums.length-1){
                    //有边界向下，左下
                    dir = ld ;
                }
            }else if(dir == ru){
                i-- ;
                j++ ;
                //考虑接下来的方向
                if(i==0){
                    //上边界
                    if(j==nums.length-1){
                        //右上角，向下
                        dir = d ;
                    }else{
                        //没在右上角，向右
                        dir = r ;
                    }
                }else if(j==nums.length-1){
                    //向下
                    dir = d ;
                }
            }

            nums[i][j] = num ;
        }



        for(i=0;i<nums.length;i++){
            for(j=0;j<nums[i].length;j++){
                System.out.print(nums[i][j]+"\t");
            }
            System.out.println();
            System.out.println();
        }
    }

}
