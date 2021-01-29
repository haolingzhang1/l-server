package com.intelasers.controller;

import com.intelasers.entity.ResultEntity;
import com.intelasers.entity.TemplateEntity;
import com.intelasers.exception.BusinessException;
import com.intelasers.repository.TemplateRepository;
import com.intelasers.services.TemplateService;
import com.intelasers.utils.BaseResultUtil;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/template")
public class TemplateController {

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    TemplateService templateService;


    /**
     * 创建/更新模板
     *
     * @param templateEntity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity saveTemplate(@RequestBody String templateEntity) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonTemplateObject = jsonObject.getJSONObject(templateEntity);
        //从前端传的数据中取template的name, description, id
        String templateName = jsonTemplateObject.getString("templateName");
        String templateDescription = jsonTemplateObject.getString("templateDesc");
        String templateIdString = jsonTemplateObject.getString("templateId");
        TemplateEntity template = new TemplateEntity();
        if (!(templateIdString == null || templateIdString.length() == 0)) {
            Long templateId = Long.parseLong(templateIdString);
            template = templateService.getTemplateById(templateId);
        }
        try {
            template.setModifyTime(new Date());
            template.setTemplateName(templateName);
            template.setTemplateName(templateDescription);
            TemplateEntity saveResult = templateRepository.save(template);
            return BaseResultUtil.resSuccess(saveResult);
        } catch (Exception e) {
            return BaseResultUtil.resFailed("保存失败！" + e.getMessage());
        }
    }

    /**
     * 删除模板
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultEntity deleteTemplate(@RequestParam("templateId") Long id) {
        int result = templateService.deleteTemplateById(id);
        if (result == 0) {
            return BaseResultUtil.resSuccess("删除成功！");
        } else {
            return BaseResultUtil.resFailed("删除失败！");
        }
    }


    /**
     * 根据模板Id模板名称及描述查找模板
     *
     * @param templateEntity
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResultEntity searchTemplate(@RequestBody String templateEntity) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonTemplateObject = jsonObject.getJSONObject(templateEntity);
        String templateName = jsonTemplateObject.getString("templateName");
        String templateDescription = jsonTemplateObject.getString("templateDesc");
        String templateIdString = jsonTemplateObject.getString("templateId");
        if(templateIdString==null){
            throw new BusinessException("无模板Id");
        }
        else{
            Long id=Long.parseLong(templateIdString);
            return  BaseResultUtil.resSuccess(templateRepository.findAllTemplates(id,templateName,templateDescription));
        }
    }

    /**
     * 根据模板Id查找模板
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/searchId", method = RequestMethod.GET)
    public ResultEntity searchTemplateByCondition(@RequestParam("templateId") Long id) {
        TemplateEntity templateEntity = templateService.getTemplateById(id);
        if (templateEntity == null) {
            return BaseResultUtil.resSuccess("该模板不存在");
        } else {
            return BaseResultUtil.resSuccess(templateEntity);

        }
    }


    /**
     * 列出所有
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity listTemplate(){
        try{
           List<TemplateEntity> templateEntityList=templateRepository.listAllTemplates();
            return BaseResultUtil.resSuccess(templateEntityList);}
        catch (Exception e) {
            return BaseResultUtil.resFailed("查询所有模板失败！" + e.getMessage());
        }
    }

}







