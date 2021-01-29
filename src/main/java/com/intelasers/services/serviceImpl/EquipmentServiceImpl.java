package com.intelasers.services.serviceImpl;


import com.intelasers.entity.EquipmentEntity;
import com.intelasers.exception.BusinessException;
import com.intelasers.repository.EquipmentRepository;
import com.intelasers.services.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService  {

    @Autowired
    EquipmentRepository equipmentRepository;

    //根据id查找设备，若不存在则返回null
    @Override
    public EquipmentEntity getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    @Override
    public Integer deleteEquipmentById(Long id){
        Integer result = 0;
        try {
            if (equipmentRepository.existsById(id)) {
                equipmentRepository.deleteById(id);
            }
            else{
                throw new BusinessException("该设备不存在，无法进行删除，请仔细核对信息！");
            }
         //todo:若历史数据需要删除，还需要对tb_param_val进行删除

        } catch (Exception e) {
            result = -1;
            log.error("delete equipment by id error, detail info is {}, {}", e.getMessage(), e.getStackTrace());
        }
        return result;
    }

}
