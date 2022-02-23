package com.duyi.examonline.dao;

import com.duyi.examonline.domain.StudentExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentExamMapper {
    int deleteByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int insert(StudentExam record);

    int insertSelective(StudentExam record);

    StudentExam selectByPrimaryKey(@Param("examId") Long examId, @Param("studentId") Long studentId);

    int updateByPrimaryKeySelective(StudentExam record);

    int updateByPrimaryKey(StudentExam record);


    List<Long> findStudentIdByExam(Long examId) ;

    void removeReference(List<StudentExam> removeStudents) ;

    void addReference(List<StudentExam> addStudents) ;

    List<Map> findRefClasses(Long examId) ;

    int findPagePathCount(Long examId);

    void updatePagePath(@Param("examId") Long examId,@Param("studentId") Long studentId,@Param("pagePath") String pagePath);
}