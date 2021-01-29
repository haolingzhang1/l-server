package com.intelasers.repository;

import com.intelasers.entity.ParameterEntity;
import com.intelasers.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParameterRepository extends JpaRepository<ParameterEntity, Long>, JpaSpecificationExecutor<ParameterEntity> {

    /**
     * 根据模板id以及参数名字确定有哪些参数
     * @param templateId,parameterName
     * @return
     */
    @Query(value = "select * from tb_parameter tp where tp.template_id =:templateId and tp.parameter_name like CONCAT('%',:parameterName,'%')", nativeQuery = true)
    List<ParameterEntity> getParameterByNameAndTemplateId(@Param("templateId")Long templateId, @Param("parameterName")String parameterName);

    /**
     * 根据模板id确定该模板有哪些参数
     * @param templateId,parameterName
     * @return
     */
    @Query(value = "select * from tb_parameter tp where tp.template_id = ?1", nativeQuery = true)
    List<ParameterEntity> getParametersByTemplateId(Long templateId);

}
