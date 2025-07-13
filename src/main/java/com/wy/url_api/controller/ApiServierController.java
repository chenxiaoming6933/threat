package com.wy.url_api.controller;

import com.wy.url_api.common.IntelligenceIocJob;
import com.wy.url_api.common.util.AuthorizedUtil;
import com.wy.url_api.domain.ApiResponseBean;
import com.wy.url_api.dto.ApiParamDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by M on 2020/2/12.
 * API 入口
 */
@RestController
@RequestMapping(value = "/intelligence")
public class ApiServierController {

    @Autowired
    private AuthorizedUtil authorizedUtil;

    @Autowired
    private IntelligenceIocJob iocJob;

    /**
     * 情报IOC结构 查询方法
     *
     * @param type
     * @param value
     * @param token
     * @return
     */
    @GetMapping(value = "/ioc/v1.0/{type}/{value}/{token}")
    public ApiResponseBean findIntelligenceIoc(@PathVariable(name = "type") String type, @PathVariable(name = "value") String value, @PathVariable(name = "token") String token) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(value) || StringUtils.isBlank(token)) {
            return new ApiResponseBean(value, type, null, 500);
        }
        if (!authorizedUtil.authorizedToken(token)) {
            return new ApiResponseBean(value, type, null, 500);
        }

        return iocJob.findInfo(type, value);
    }


    @PostMapping(value = "/ioc/v2.0")
    private ApiResponseBean findIntelligenceIoc(@Valid @RequestBody ApiParamDTO paramDTO) {
        return iocJob.findInfo(paramDTO.getType(), paramDTO.getVaule());
    }
}
