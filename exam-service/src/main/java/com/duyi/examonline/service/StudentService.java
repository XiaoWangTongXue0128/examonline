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

    /**
     * 获得某一个班级中，排除某一个学生后的，其他所有学生的id组合
     * 考试信息模块使用的功能
     */
    String findStudentIdsExcludeId(String className , Long studentId);


    /**
     * 根据一组班级名称的字符串，获得其中所有班级的信息（班级名-className + 总人数-total）
     * @param classNames
     * @return
     */
    List<Map> findClassesByNames(String classNames) ;

    List<Student> findExistStudent(List<Student> studentList) ;

    boolean isExistClass(String className) ;


    Student findByName(String sname) ;

    void updatePwd(Long id , String pass) ;
}
