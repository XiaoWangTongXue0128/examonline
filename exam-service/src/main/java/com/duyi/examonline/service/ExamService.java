package com.duyi.examonline.service;

import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.vo.PageVO;

import java.util.List;
import java.util.Map;

public interface ExamService {

    PageVO findByPage(int page , int rows , Map condition);

    /**
     * @param exam
     * @Throws DuplicateKeyException 当考试名称重复时抛出该异常
     */
    void save(Exam exam );


    Exam findById(Long id) ;

    /**
     * @param exam
     * @Throws DuplicateKeyException 当考试名称重复时抛出该异常
     */
    void update(Exam exam);

    List<Map<String,Object>> findBindStudents(String className,String[] sidArray) ;

    List<Map<String,Object>> findUnbindStudents(String className,String[] sidBindArray) ;

    /**
     * 获取指定班级中所有学生id组成的字符串
     * @param className
     * @return
     */
    String findClassAllStudentIds(String className) ;

    void fill(Exam exam , Map<String,String> classesCache) ;

    List<Map> findRefClasses(Long id) ;

    boolean isPageExist(Long id);

    void generatePage(Long id) ;

    boolean removePage(Long id) ;

    boolean removeExam(Long id) ;
}
