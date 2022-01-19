package com.duyi.examonline.service;

import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.vo.PageVO;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * 批量保存
     * 允许部分成功，部分失败
     * @param students
     * @return 保存结果
     * @see "com.duyi.examonline.service.TeacherService.saves()"
     */
    String saves(List<Student> students);

    /**
     * @param student
     * @throws DuplicateKeyException 抛出异常表示名称或学号重复
     */
    void save(Student student) ;

    /**
     * 分页过滤查询
     * @param pageNo
     * @param condition 可能包含3个条件{grade , major , classNo}
     */
    PageVO findClasses(int pageNo , Map condition) ;


    /**
     *
     * @param condition 要求必须包含 code , sname , className 3者之一
     * @return
     */
    List<Student> findStudents(Map condition) ;

    Student findStudentById(Long id);

    void update(Student student) ;

    /**
     * 可以删除多个班级的学生信息
     * @param classNames  多个班级使用逗号隔开
     *                    格式： 2020-软件-1班,2020-软件-2班,
     */
    void deleteByClasses(String classNames) ;

    void deleteStudent(Long id) ;

    /**
     * @param ids "1,2,3,4,5"
     */
    void deleteStudents(String ids) ;

    List<Student> findStudentsByClasses(Map condition);
}
