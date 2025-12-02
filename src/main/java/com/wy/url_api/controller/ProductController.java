package com.wy.url_api.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Object findProductDetail(@RequestParam String id) {
        if (StringUtils.isNoneBlank(id)) {
            return new Object();
        }
        return null;
    }
}
