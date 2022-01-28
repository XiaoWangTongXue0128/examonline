package com.duyi.examonline.service.impl;

import com.duyi.examonline.common.CommonUtil;
import com.duyi.examonline.dao.TemplateMapper;
import com.duyi.examonline.domain.Template;
import com.duyi.examonline.domain.vo.PageVO;
import com.duyi.examonline.service.TemplateService;
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
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper ;

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

        return CommonUtil.pageCast(pageInfo,condition);
    }
}
