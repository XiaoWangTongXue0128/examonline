package com.duyi.examonline.service;

import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.StudentExam;
import com.duyi.examonline.domain.dto.StudentExamDTO;
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

    void releaseExam(Long id) ;

    /**
     * @param timeFlag 1 当天， 2 本周， 3 本月
     * @return  List.Map{examId , name , startTime,endTime,duration , state , status}
     */
    List<Map> findByStudent(Long sid , Integer timeFlag);

    StudentExam findStudentExamById(Long studentId , Long examId) ;

    void startExam(Long studentId , Long examId) ;

    void updateStartTime(Long studentId , Long examId) ;

    void updateAnswer(Map answerInfo);

    void update(StudentExam se);

    void submitPage(Map answerInfo) ;

    /**
     * 丢弃考试
     * @param id
     */
    void leaveExam(Long id) ;

    /**
     * 手动结束考试（时长）
     * @param id
     */
    void finishExam(Long id) ;

    /**
     * 查询获得指定考试的班级信息列表
     * @param id
     * @return List.map{className:'',count:0}
     */
    List<Map> findClassesByExam(Long id);


    List<StudentExamDTO> findStudentsByExamAndClass(Long examId ,String className) ;

    void changeStudentStatus(Long examId , Long studentId , String status) ;

    void review(StudentExam studentExam) ;

    void submit(Long examId) ;
}
