package com.wy.url_api.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName 用户相关接口
 * @Author XiaoMing
 * @Date
 * @Vsersion V1.0
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    /**
     * 测试master
     * @param type
     * @param jsonParam
     * @return
     */
    @GetMapping(value = "/show/users.do")
    public Map<Object, Object> findAllUser(@RequestParam(name = "queryType",defaultValue = "all") String type, @RequestBody JSONObject jsonParam) {
        return new TreeMap <>();
    }
}
