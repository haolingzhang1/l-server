package com.intelasers.controller;

import com.intelasers.entity.EquipmentEntity;
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

import java.util.List;

@CrossOrigin
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
    @RequestMapping(value = "/save", method = RequestMethod.GET)
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
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultEntity deleteParameter(@RequestParam("templateId") Long id) {
        int result = parameterService.deleteParameterById(id);
        if(result==0){
            return BaseResultUtil.resSuccess("删除成功！");
        }else{
            return BaseResultUtil.resFailed("删除失败！");
        }
    }

    /**
     * 根据模板Id以及参数名字模糊查找对应的参数信息
     * @param
     * @return
     */
    @RequestMapping(value = "/listByConditions", method = RequestMethod.GET)
    public ResultEntity listParameterByCondition(@RequestParam("templateId") Long templateId,@RequestParam("parameterName")String parameterName) {
        try {

            List<ParameterEntity> parameterEntityList=parameterRepository.getParameterByNameAndTemplateId(templateId, parameterName);
            return BaseResultUtil.resSuccess(parameterEntityList);
        }
        catch(Exception e){
            return BaseResultUtil.resFailed("查找失败，具体信息为: "+e.getMessage());
        }
    }

    /**
     * 根据模板Id查找对应的参数列表
     * @param
     * @return
     */
    @RequestMapping(value = "/listByTemplate", method = RequestMethod.GET)
    public ResultEntity listParameterByTemplate(@RequestParam("templateId") Long templateId) {
        try {

            List<ParameterEntity> parameterEntityList=parameterRepository.getParametersByTemplateId(templateId);
            return BaseResultUtil.resSuccess(parameterEntityList);
        }
        catch(Exception e){
            return BaseResultUtil.resFailed("查找失败，具体信息为: "+e.getMessage());
        }
    }

}
