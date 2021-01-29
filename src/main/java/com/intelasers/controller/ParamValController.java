package com.intelasers.controller;

import com.intelasers.entity.ParamValEntity;
import com.intelasers.entity.ResultEntity;
import com.intelasers.repository.ParamValRepository;
import com.intelasers.services.ParamValService;
import com.intelasers.utils.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/paramval")
public class ParamValController {

    @Autowired
    ParamValService paramValService;

    @Autowired
    ParamValRepository paramValRepository;

   //单个参数的保存
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultEntity saveParamVal(@RequestParam("paramVal") String paramVal,
                                     @RequestParam("paramId") String paramId,
                                     @RequestParam("templateId") String templateId,
                                     @RequestParam("roundId") String roundId) throws JSONException {
    try{
        ParamValEntity paramValEntity=new ParamValEntity();
        paramValEntity.setParamVal(paramVal);
        //todo: paramId通过templateId和paramName直接从数据里查找，不需要前端手动输入
        paramValEntity.setParamId(Long.parseLong(paramId));
        paramValEntity.setRoundId(Long.parseLong(roundId));
        return BaseResultUtil.resSuccess(paramValRepository.save(paramValEntity));
    }catch (Exception e){
        return BaseResultUtil.resFailed("保存失败！" + e.getMessage());
    }
    }





    //todo：模板选定后，所需参数一起保存
}
