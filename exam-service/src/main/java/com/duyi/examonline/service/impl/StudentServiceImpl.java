package com.duyi.examonline.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.duyi.examonline.common.CommonData;
import com.duyi.examonline.dao.StudentMapper;
import com.duyi.examonline.domain.Student;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper ;

    private Logger log = LoggerFactory.getLogger(this.getClass()) ;

    @Override
    public void save(Student student) {
        //完善student
        student.setMnemonicCode(PinyinUtil.getPinyin(student.getSname(),""));

        student.setPass( DigestUtil.md5Hex(student.getPass()) );

        try{
            studentMapper.insert(student) ;
        }catch (DuplicateKeyException e){
            log.debug("保存时，学号或名称重复 [{}],[{}]",student.getCode(),student.getSname());
            throw e ;
        }
    }

    @Override
    public String saves(List<Student> students) {
        String msg = "";
        int count1 = 0 ;
        int count2 = 0 ;
        for(Student student : students){
            try{
                this.save(student);
                count1++ ;
            }catch (DuplicateKeyException e){
                count2++ ;
                msg += "【"+student.getCode()+","+student.getSname()+"】因为学号或名称重复导致失败|" ;
            }
        }

        msg = "共导入记录【"+(count1+count2)+"】条|"+"成功导入记录【"+count1+"】条|"+"失败导入记录【"+count2+"】条|" +  msg ;

        log.debug("msg {}",msg);

        return msg;
    }

    @Override
    public PageVO findClasses(int pageNo, Map condition) {
        PageHelper.startPage(pageNo, CommonData.DEFAULT_ROWS);
        List<Map> classes = studentMapper.findClasses(condition);
        PageInfo info = new PageInfo(classes) ;
        //将插件提供的PageInfo对象，组成我们之前更习惯使用的PageVO

        return new PageVO(
                info.getPageNum(),
                info.getPageSize(),
                info.getTotal(),
                info.getNavigateLastPage(),
                (int)info.getStartRow(),
                (int)info.getEndRow(),
                info.getList(),
                condition
            ) ;

    }

    @Override
    public List<Student> findStudents(Map condition) {
        return studentMapper.findStudents(condition);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Student student) {
        student.setMnemonicCode( PinyinUtil.getPinyin(student.getSname(),"") );

        try{
            studentMapper.updateByPrimaryKeySelective(student) ;
        }catch (DuplicateKeyException e){
            log.debug("修改时，学号或名称重复 [{}],[{}]",student.getCode(),student.getSname());
            throw e ;
        }
    }


    @Override
    public void deleteByClasses(String classNames) {
        studentMapper.deleteByClasses(classNames);
    }

    @Override
    public void deleteStudent(Long id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteStudents(String ids) {
        //一条sql也可以实现批量删除  delete from t_student id in (1,2,3,4,5)
        //in中的条件内容恰好和我们传递的ids值 格式一样
        //此时考虑将ids值原样拼装在sql中。  mybatis的${}支持原样拼串
        studentMapper.deletes(ids);
    }

    @Override
    public List<Student> findStudentsByClasses(Map condition) {
        return studentMapper.findStudentsByClasses(condition);
    }
}
