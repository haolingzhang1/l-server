package com.intelasers.controller;

import com.intelasers.entity.EquipmentEntity;
import com.intelasers.entity.ResultEntity;

import com.intelasers.exception.BusinessException;
import com.intelasers.repository.EquipmentRepository;
import com.intelasers.services.EquipmentService;
import com.intelasers.utils.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    EquipmentService equipmentService;


    /**
     * 创建/更新设备信息
     * @param equipmentEntity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity saveEquipment(@RequestBody String equipmentEntity) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonTemplateObject = jsonObject.getJSONObject(equipmentEntity);
        //从前端传的数据中取需要更新或者新建的设备的具体参数信息,以及需要修改信息的设备id
        String eUnit = jsonTemplateObject.getString("unit");
        String eSeq = jsonTemplateObject.getString("seq");
        String ePkt = jsonTemplateObject.getString("pkt");
        String templateIdString = jsonTemplateObject.getString("templateId");
        String equipmentIdString = jsonTemplateObject.getString("id");
        EquipmentEntity equipment = new EquipmentEntity();
        if (templateIdString == null || templateIdString.length() == 0) {
            throw new BusinessException("请输入该设备对应的模板id");
        }
        Long templateId = Long.parseLong(templateIdString);
        if (!(equipmentIdString == null || equipmentIdString.length() == 0)) {
            Long equipmentId = Long.parseLong(equipmentIdString);
            equipment = equipmentService.getEquipmentById(equipmentId);
        }
        try{
            equipment.setTemplateId(templateId);
            equipment.setPkt(ePkt);
            equipment.setUnit(eUnit);
            equipment.setSeq(eSeq);
            EquipmentEntity saveResult = equipmentRepository.save(equipment);
            return BaseResultUtil.resSuccess(saveResult);
        }catch (Exception e){
            return BaseResultUtil.resFailed("保存失败！" + e.getMessage());
        }
    }

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultEntity deleteEquipment(@RequestParam("equipmentId") Long id) {
        int result = equipmentService.deleteEquipmentById(id);
        if(result==0){
            return BaseResultUtil.resSuccess("删除成功！");
        }else{
            return BaseResultUtil.resFailed("删除失败！");
        }
    }


    /**
     * 列出所有设备信息
     * @param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultEntity listEquipment() {
        try {
            List<EquipmentEntity> equipmentEntityList = equipmentRepository.findAll();
            return BaseResultUtil.resSuccess(equipmentEntityList);
        }
        catch(Exception e){
            return BaseResultUtil.resFailed("查找失败，具体信息为: "+e.getMessage());
        }
        }

    }



