package com.duyi.examonline.service;

import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.vo.PageVO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {

    /**
     *
     * @param teacher
     * @throws DuplicateKeyException 抛出该异常，表示保存失败。 否则表示保存成功
     */
    void save(Teacher teacher) ;

    Teacher findByName(String tname) ;

    void updatePwd(Long id , String pass) ;

    /**
     * 要求传入的page和rows有效的  不能为null
     * @param page
     * @param rows
     * @param tname
     */
    PageVO find(int page , int rows , String tname) ;

    Teacher findById(Long id) ;

    void update(Teacher teacher) ;

    void deleteAll(String ids) ;

    /**
     * 虽然是批量保存，但允许有的失败，有的成功
     * 将失败和成功的信息拼成字符串返回
     * @param teachers
     * @return 共导入记录【x】条|
     *         成功导入记录【y】条|
     *         失败导入记录【z】条|
     *         【xxx1】记录，因为名称重复导致失败|
     *         【xxx2】记录，因为名称重复导致失败|
     *         【xxx3】记录，因为名称重复导致失败|
     *         【xxx4】记录，因为名称重复导致失败|
     *          注意：最终的字符串，没有换行，每部分使用|隔开。使用者可以根据需求，对|进行转换（换行）
     */
    String saves(List<Teacher> teachers) ;

    List<Teacher> findAll();

}
