package com.intelasers.services.serviceImpl;

import com.intelasers.exception.BusinessException;
import com.intelasers.repository.ParameterRepository;
import com.intelasers.services.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    ParameterRepository parameterRepository;

    //删除设备
    @Override
    public Integer deleteParameterById(Long id){
        Integer result = 0;
        try {
            if (parameterRepository.existsById(id)) {
                parameterRepository.deleteById(id);
            }
            else{
                throw new BusinessException("该参数不存在，无法进行删除，请仔细核对信息！");
            }
        } catch (Exception e) {
            result = -1;
            log.error("delete parameter by id error, detail info is {}, {}", e.getMessage(), e.getStackTrace());
        }
        return result;
    }

}
