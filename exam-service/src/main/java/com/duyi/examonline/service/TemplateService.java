package com.duyi.examonline.service;

import com.duyi.examonline.domain.Template;

public interface TemplateService {

    /**
     *
     * @param template
     * @throws org.springframework.dao.DuplicateKeyException 模板名重复时会抛出该异常
     */
    void save(Template template) ;

}
