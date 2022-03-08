package com.duyi.examonline.service.impl;

import com.duyi.examonline.dao.DictionaryMapper;
import com.duyi.examonline.domain.Dictionary;
import com.duyi.examonline.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper ;

    @Override
    public List<String> findMajors() {
        return dictionaryMapper.findMajors();
    }

    @Override
    public List<String> findCourses() {
        return dictionaryMapper.findCourses();
    }
}
