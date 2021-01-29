package com.intelasers.controller;

import com.intelasers.entity.ParameterEntity;
import com.intelasers.entity.ResultEntity;
import com.intelasers.exception.BusinessException;
import com.intelasers.repository.ParameterRepository;
import com.intelasers.services.ParameterService;
import com.intelasers.utils.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/parameter")
public class ParameterController {

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    ParameterService parameterService;


    /**
     * 创建或者修改每个模板对应的参数
     * @param parameterName,templateId
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity saveParameter(@RequestParam("parameterName") String parameterName,
                                     @RequestParam("templateId") String templateIdString) throws JSONException {
        try{
        ParameterEntity parameterEntity = new ParameterEntity();
        if(templateIdString == null|| templateIdString.length()==0){
                throw new BusinessException("请输入模板编号！");
        }
            Long templateId = Long.parseLong(templateIdString);
            parameterEntity.setTemplateId(templateId);
            parameterEntity.setParamName(parameterName);
            ParameterEntity saveResult = parameterRepository.save(parameterEntity);
            return BaseResultUtil.resSuccess(saveResult);
        }catch (Exception e){
            return BaseResultUtil.resFailed("保存失败！" + e.getMessage());
        }
    }


    /**
     * 删除模板中的某一个参数
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity deleteParameter(@RequestParam("templateId") Long id) {
        int result = parameterService.deleteParameterById(id);
        if(result==0){
            return BaseResultUtil.resSuccess("删除成功！");
        }else{
            return BaseResultUtil.resFailed("删除失败！");
        }
    }

}
