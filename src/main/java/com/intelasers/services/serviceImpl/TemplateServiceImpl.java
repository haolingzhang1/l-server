package com.intelasers.services.serviceImpl;

import com.intelasers.entity.TemplateEntity;
import com.intelasers.exception.BusinessException;
import com.intelasers.repository.TemplateRepository;
import com.intelasers.services.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public TemplateEntity getTemplateById(Long id) {
        return templateRepository.getTemplateById(id);
    }

    @Override
    public Integer deleteTemplateById(Long id){
        Integer result = 0;
        try {
            if (templateRepository.existsById(id)){
                templateRepository.deleteById(id);
            }
            else{
                throw new BusinessException("该模板不存在，无法进行删除，请仔细核对信息！");
            }
            //todo: parameter表里与该模板有关的参数是否需要删除
            //todo: 以及tb_param_val用于记录之前测试时的参数数据时，记录改模板的参数是否需要删除
            //todo:还有tb_equipment表里也有该数据
        } catch (Exception e) {
            result = -1;
            log.error("delete template by id error, detail info is {}, {}", e.getMessage(), e.getStackTrace());
        }
        return result;
    }

    }



