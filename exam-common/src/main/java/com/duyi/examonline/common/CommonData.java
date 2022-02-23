package com.duyi.examonline.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储一些公共常量
 */
public interface CommonData {

    int DEFAULT_PAGE = 1 ;

    int DEFAULT_ROWS = 5 ;

    String DEFAULT_PASS = "123" ;

    String DEFAULT_TEMPLATE_STATUS = "私有" ;

    String DEFAULT_QUESTION_STATUS = DEFAULT_TEMPLATE_STATUS ;

    String SEPARATOR = "}-|-{" ;

    String SPLIT_SEPARATOR = "\\}-\\|-\\{" ;

    String OPTION_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;

    Map DEFAULT_CONDITION = new HashMap();

    String DEFAULT_EXAM_STATUS = "未发布" ;

    String DEFAULT_STUDENT_EXAM_STATUS = "未考试" ;

    String PAGE_ROOT_PATH = "f"+ File.separator+"z" ;

    String STATIC_PAGE_NAME = "page.txt" ;

    String QUESTION_OPTION_SEPARATOR = "--\r\n" ;

    String QUESTION_SEPARATOR = "====" ;
}
