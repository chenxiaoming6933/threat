package com.wy.url_api.common;

import com.wy.url_api.domain.ApiResponseBean;
import com.wy.url_api.domain.ApiConfigBean;
import com.wy.url_api.service.IntelligenceIocService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by M on 2020/2/13.
 * API 情报IOC类型 处理类
 */
@Component
public class IntelligenceIocJob {


    @Autowired
    private IntelligenceIocService service;

    /**
     * 根据类别 调用不同方法 获取数据
     *
     * @param type
     * @return
     */
    public ApiResponseBean findInfo(String type, String value) {
        if (!StringUtils.isNotBlank(type) || !StringUtils.isNotBlank(value)) {
            return new ApiResponseBean(null, null, null, 500);
        }

        switch (type) {
            case ApiConfigBean.REQUEST_TYPE_URL:
                return service.findUrlInfo(value, type);
            case ApiConfigBean.REQUEST_TYPE_IPV4:
                return service.findIpInfo(value, type);
            case ApiConfigBean.REQUEST_TYPE_DOMAIN:
                return service.findDomainInfo(value, type);
            case ApiConfigBean.REQUEST_TYPE_EMAIL:
                return service.findEmailInfo(value, type);
            case ApiConfigBean.REQUEST_TYPE_MD5:
                return service.findMd5Info(value, type);
            case ApiConfigBean.REQUEST_TYPE_SHA1:
                return service.findSha1Info(value, type);
            case ApiConfigBean.REQUEST_TYPE_SHA256:
                return service.findSha256Info(value, type);
            case ApiConfigBean.REQUEST_TYPE_DNS:
                return service.findDnsInfo(value, type);
            default:
                return new ApiResponseBean(value, type, null, 500);
        }
    }
}
