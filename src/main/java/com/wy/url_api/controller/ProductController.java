package com.wy.url_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 商品相关接口
 * @Author XiaoMing
 * @Date 2020/8/25 17:04
 * @Vsersion V1.0
 * @Desrciption TODO
 */
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    @GetMapping(value = "/{ids}")
    public List <Object> findAllProdcts(@PathVariable(name = "ids") String ids) {
        return new ArrayList <>();
    }
}
