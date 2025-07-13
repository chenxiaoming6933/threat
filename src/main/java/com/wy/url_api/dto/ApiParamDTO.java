package com.wy.url_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @ClassName api 请求对象DTO
 * @Author XiaoMing
 * @Date 2025/7/13 10:06
 * @Vsersion V1.0
 * @Desrciption TODO
 */
@Data
@EqualsAndHashCode
public class ApiParamDTO {


    /**
     * 请求类型
     */
    @NotNull
    private String type;

    /**
     * 查询内容
     */
    @NotNull
    private String vaule;

    /**
     * 请求token
     */
    @NotNull
    private String token;


}
