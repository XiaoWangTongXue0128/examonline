package com.duyi.examonline.dao;

import com.duyi.examonline.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);


    List<Question> findByTypeAndStatusAndCourse(@Param("type")String type , @Param("level")String level,@Param("course")String course);
}