package com.duyi.examonline.service.impl;

import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.dao.ExamMapper;
import com.duyi.examonline.dao.StudentExamMapper;
import com.duyi.examonline.dao.StudentMapper;
import com.duyi.examonline.domain.Exam;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.StudentExam;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.ExamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper ;

    @Autowired
    private StudentMapper studentMapper ;

    @Autowired
    private StudentExamMapper studentExamMapper ;

    @Override
    public PageVO findByPage(int page, int rows, Map condition) {

        PageHelper.startPage(page, rows) ;
        List<Exam> exams = examMapper.find(condition);
        PageInfo info = new PageInfo(exams);

        //...

        return CommonUtil.pageCast(info,condition);
    }

    @Override
    public void save(Exam exam) {
        examMapper.insert(exam);
    }

    @Override
    public Exam findById(Long id) {
        return examMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Exam exam) {
        examMapper.updateByPrimaryKeySelective(exam);
    }

    @Override
    public List<Map<String, Object>> findBindStudents(String className,String[] sidArray) {
        return studentMapper.findBindStudents(className,sidArray);
    }

    @Override
    public List<Map<String, Object>> findUnbindStudents(String className, String[] sidBindArray) {
        return studentMapper.findUnbindStudents(className,sidBindArray);
    }

    @Override
    public String findClassAllStudentIds(String className) {
        return studentMapper.findClassAllStudentIds(className);
    }

    @Override
    public void fill(Exam exam, Map<String, String> classesCache) {
        //先对exam对象做修改 (templateId , duration , startTime , endTime)
        examMapper.updateByPrimaryKeySelective(exam) ;

        //更新学生关联信息
        //先获得当前考试的上一次的关联信息
        List<Long> oldSidList = studentExamMapper.findStudentIdByExam(exam.getId());
        //接下来就需要将oldSidList中的学生id 和 classesCache中的学生id比对
        //找到多的增加，找到少的删除
        List<StudentExam> addList = new ArrayList<>();
        List<StudentExam> removeList = new ArrayList<>();

        //比较时，需要先将缓存中的信息变成学生id列表。
        //逐个的将班级中的人员信息取出
        String[] classNameArray = new String[classesCache.size()] ;
        classesCache.keySet().toArray(classNameArray);
        //学生与班级的对应关系
        //key = set 装载班级关联的学生id
        //value = string 存储班级名称
        Map<Set,String> map = new HashMap<>();
        for(String className : classNameArray){
            // 3种 "ALL","1,2,3,4,5","x,1,2,3,4,5"
            String info = classesCache.get(className);
            if("ALL".equals(info)){
                //获取这个班级所有的学生id组成的字符串 ALL->"1,2,3,4,5,6"
                info = studentMapper.findClassAllStudentIds(className);
            }else if(info.startsWith("x")){
                //"x,1,2,3" --> "1,2,3"
                info = info.replace("x,","");
            }

            Set<String> idSet = new HashSet<>( Arrays.asList( info.split(",") ) );
            map.put(idSet , className) ;
        }

        //代码至此，就将新数据以{set学生：班级}装载
        //接下来获得需要增加和删除的学生id
        Set<Set> sets = map.keySet();

        //装载所有新学生的id
        Set<String> newSidSet = new HashSet<String>();
        for(Set set : sets){
            newSidSet.addAll(set);
        }

        //装载上一次学生的id
        Set<String> oldSidSet = new HashSet<>();
        for(Long sid : oldSidList){
            oldSidSet.add(String.valueOf(sid)) ;
        }

        Set<String> all = new HashSet<>();
        all.addAll(newSidSet);
        all.addAll(oldSidSet) ;

        //此时all中存储的就是需要增加的学生信息
        all.removeAll(oldSidSet);
        Long examId = exam.getId() ;
        for(String sid : all){
            StudentExam se = new StudentExam() ;
            addList.add(se) ;
            se.setStatus(CommonData.DEFAULT_STUDENT_EXAM_STATUS);
            se.setExamId( examId );
            se.setStudentId(Long.valueOf(sid));
            for(Set set : sets){
                if(set.contains(sid)){
                    String className = map.get(set);
                    se.setExamGroup(className);
                    break ;
                }
            }
        }

        all.addAll(oldSidSet);
        //此时all中储存胡的就是需要删除的学生信息
        all.removeAll(newSidSet) ;
        for(String sid:all){
            StudentExam se = new StudentExam() ;
            removeList.add(se) ;
            se.setExamId( examId );
            se.setStudentId(Long.valueOf(sid));
        }

        //移除
        if(removeList.size() > 0){
            studentExamMapper.removeReference(removeList);
        }

        //添加
        if(addList.size() > 0){
            studentExamMapper.addReference(addList);
        }
    }

    @Override
    public List<Map> findRefClasses(Long examId) {
        return studentExamMapper.findRefClasses(examId);
    }
}
