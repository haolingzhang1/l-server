package com.intelasers.services.serviceImpl;

import com.intelasers.exception.BusinessException;
import com.intelasers.repository.ParamValRepository;
import com.intelasers.repository.ParameterRepository;
import com.intelasers.services.ParamValService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParamValServiceImpl implements ParamValService {
    @Autowired
    ParamValRepository paramValRepository;



}
