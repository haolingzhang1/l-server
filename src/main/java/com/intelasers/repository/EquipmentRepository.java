package com.intelasers.repository;

import com.intelasers.entity.EquipmentEntity;
import com.intelasers.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long>, JpaSpecificationExecutor<EquipmentEntity> {
    /**
     * 根据id查询模板
     * @param id
     * @return
     */
    @Query(value = "select * from tb_equipment where id = ?1", nativeQuery = true)
    TemplateEntity getEquipmentById(Long id);

}
