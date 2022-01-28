package com.duyi.examonline.dao;

import com.duyi.examonline.domain.Template;

import java.util.List;
import java.util.Map;

public interface TemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    List<Template> find(Map condition) ;
}