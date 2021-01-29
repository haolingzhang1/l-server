package com.intelasers.repository;

import com.intelasers.entity.ParamValEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParamValRepository extends JpaRepository<ParamValEntity, Long>, JpaSpecificationExecutor<ParamValEntity> {
}
