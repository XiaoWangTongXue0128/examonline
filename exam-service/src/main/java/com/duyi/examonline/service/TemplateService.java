package com.duyi.examonline.service;

import com.duyi.examonline.domain.Template;
import com.duyi.examonline.domain.vo.PageVO;

import java.util.Map;

public interface TemplateService {

    /**
     *
     * @param template
     * @throws org.springframework.dao.DuplicateKeyException 模板名重复时会抛出该异常
     */
    void save(Template template) ;

    /**
     *
     * @param page
     * @param rows
     * @param condition {name , course , type , status , tid , shareid}
     *                  tid: 必须存在，当前老师id
     *                  shareid：可选，分享模板某一个老师id
     * @return
     */
    PageVO find(int page , int rows, Map condition) ;


    boolean delete(Long id , Long tid);

    void changeStatus(Long id,String status) ;

    void shareTemplate(Long templateId , Long teacherId) ;
}
