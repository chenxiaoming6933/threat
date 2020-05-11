package com.wy.url_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 用户相关接口
 * @Author XiaoMing
 * @Date
 * @Vsersion V1.0
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {
    
    @GetMapping(value = "/show/users.do")
    public Map<Object, Object> findAllUser(@RequestParam(name = "queryType",defaultValue = "all") String type) {
        return new HashMap <>();
    }
}
