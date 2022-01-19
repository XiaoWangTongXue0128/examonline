package com.duyi.examonline.service;

import java.util.List;

public interface DictionaryService {

    /**
     * 查询所有的专业信息
     * @return
     */
    List<String> findMajors();

}
