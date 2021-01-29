package com.intelasers.controller;

import com.intelasers.entity.ResultEntity;
import com.intelasers.entity.TemplateEntity;
import com.intelasers.utils.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/mail")
public class MailController {

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ResultEntity sendMail(@RequestBody String mailInfomation) throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonTemplateObject = jsonObject.getJSONObject(mailInfomation);
            System.out.println(mailInfomation);
            String context = jsonTemplateObject.getString("context");
            return BaseResultUtil.resSuccess(context);
        }
        catch(Exception e){
            log.info(mailInfomation);
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonTemplateObject = jsonObject.getJSONObject(mailInfomation);
            log.info(jsonTemplateObject.getString("context"));
            return BaseResultUtil.resFailed("发送失败"+e.getMessage());}
        }
    }


