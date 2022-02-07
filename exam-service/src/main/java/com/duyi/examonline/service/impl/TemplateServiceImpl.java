package com.duyi.examonline.service.impl;

import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.dao.TeacherMapper;
import com.duyi.examonline.dao.TemplateMapper;
import com.duyi.examonline.domain.Teacher;
import com.duyi.examonline.domain.Template;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.domain.vo.TemplateVO;
import com.duyi.examonline.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper ;

    @Autowired
    private TeacherMapper teacherMapper ;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void save(Template template) {
        try {
            templateMapper.insert(template);
        }catch (DuplicateKeyException e){
            log.debug("模板名称重复 [{}]",template.getName());
            throw e ;
        }
    }

    @Override
    public PageVO find(int page, int rows, Map condition) {

        PageHelper.startPage(page,rows) ;
        List<Template> templates = templateMapper.find(condition);
        PageInfo<Template> pageInfo = new PageInfo(templates);

        PageVO pageVO = CommonUtil.pageCast(pageInfo,condition);

        List<Template> templateList = (List<Template>) pageVO.getData();
        List<TemplateVO> templateVOList = new ArrayList<>();

        for(Template template : templateList){
            TemplateVO vo = new TemplateVO();
            vo.setId( template.getId() );
            vo.setName( template.getName() );
            vo.setQuestion1( template.getQuestion1() );
            vo.setQuestion2( template.getQuestion2() );
            vo.setQuestion3( template.getQuestion3() );
            vo.setQuestion4( template.getQuestion4() );
            vo.setQuestion5( template.getQuestion5() );
            vo.setStatus( template.getStatus() );
            vo.setTid( template.getTid() );
            vo.setType( template.getType() );
            vo.setYl1( template.getYl1() );
            vo.setYl2( template.getYl2() );
            vo.setYl3( template.getYl3() );
            vo.setYl4( template.getYl4() );
            vo.setCreateTime( template.getCreateTime() );
            vo.setUpdateTime( template.getUpdateTime() );
            vo.setTotalScore( template.getTotalScore() );

            Teacher teacher = teacherMapper.selectByPrimaryKey(template.getTid());
            vo.setTeacher(teacher);

            //只有当前老师的模板，才关心分享给了哪些其他老师。
            //如果当前这个模板时其他老师分享给我的，我是不需要知道这个模板还被分享给了哪些其他老师
            Long tid = (Long) condition.get("tid");
            if(tid .equals(template.getTid()) ){
                List<Teacher> shareTeachers = teacherMapper.findByShareTo(template.getId());
                vo.setShareTeachers(shareTeachers);
            }

            templateVOList.add(vo);
        }

        pageVO.setData(templateVOList);

        return pageVO ;
    }

    @Override
    public boolean delete(Long id, Long tid) {

        int count = templateMapper.delete(id, tid);

        return count > 0;
    }

    @Override
    public void changeStatus(Long id, String status) {
        Template template = new Template() ;
        template.setId(id);
        template.setStatus(status);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void shareTemplate(Long templateId, Long teacherId) {
        try{
            templateMapper.share(templateId,teacherId);
        }catch (DuplicateKeyException e){
            log.warn("repeat share");
        }
    }

    @Override
    public Template findById(Long id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }
}
