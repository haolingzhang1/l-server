package com.intelasers.services;

import com.intelasers.entity.TemplateEntity;

public interface TemplateService {

    //通过模板ID查询该模板
    TemplateEntity getTemplateById(Long id);

    //通过模板ID进行删除
    Integer deleteTemplateById(Long id);

}
