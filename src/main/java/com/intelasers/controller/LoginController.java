package com.intelasers.controller;

import com.intelasers.entity.ResultEntity;
import com.intelasers.entity.TemplateEntity;
import com.intelasers.services.TemplateService;
import com.intelasers.utils.BaseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
//@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    TemplateService templateService;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ResultEntity login() {
//        //todo: 未实现
//
//
//
//            return BaseResultUtil.resSuccess("登陆成功");
//    }
@CrossOrigin
@PostMapping(value = "/api/user/login")
@ResponseBody
public Map login() {
    HashMap<String, Object> response = new HashMap<>();
    HashMap<String, Object> responseData = new HashMap<>();
    responseData.put("token",1);
    response.put("code",20000);
    response.put("msg","登录成功");
    response.put("data",responseData);
    return response;
}
    @CrossOrigin
    @GetMapping(value = "/api/user/info")
    @ResponseBody
    public Map info() {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("roles","admin");
        responseData.put("name","Super admin");
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",20000);
        responseInfo.put("msg","登录成功");
        responseInfo.put("data",responseData);
        return responseInfo;
    }

}
