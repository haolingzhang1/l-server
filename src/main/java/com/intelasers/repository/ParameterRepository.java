package com.intelasers.repository;

import com.intelasers.entity.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParameterRepository extends JpaRepository<ParameterEntity, Long>, JpaSpecificationExecutor<ParameterEntity> {

}
