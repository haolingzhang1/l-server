package com.intelasers.services;

import com.intelasers.entity.EquipmentEntity;

public interface EquipmentService {

    //查找设备
     EquipmentEntity getEquipmentById(Long id);

    //删除设备
    Integer deleteEquipmentById(Long id);

}
