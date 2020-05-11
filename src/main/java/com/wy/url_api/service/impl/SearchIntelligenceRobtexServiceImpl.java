package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.domain.ApiConfigBean;
import com.wy.url_api.service.SearchIntelligenceIocRobtexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by M on 2020/2/11.
 * IP_REQUEST_URL  这个接口里面有 FDNS RDNS的相关信息  但是情报IOC的结构里面 没有相关字段
 */
@Service
public class SearchIntelligenceRobtexServiceImpl implements SearchIntelligenceIocRobtexService {

    private static final String DNS_REQUEST_URL = "https://freeapi.robtex.com/ipquery/";

    //private static final String R_DNS_REQUEST_URL = "https://freeapi.robtex.com/pdns/forward/";

  /*  @Override
    public String findIntelligenceIocIpInfo(String ip) {

    }*/

    @Override
    public String findIntelligenceDomainInfo(String domain) {
        return null;
    }

    @Override
    public String findIntelligenceReverseDnsInfo(String ip) {
        JSONObject result = new JSONObject();
        try {
            if (StringUtils.isEmpty(ip)) {
                return result.toString();
            }
            String response = HttpClientUtil.sendGetData(DNS_REQUEST_URL + ip, ApiConfigBean.URL_ENCODE_UTF_NAME);
            if (StringUtils.isNoneEmpty(response)) {
                JSONObject responseJson = JSON.parseObject(response);
                if (!responseJson.isEmpty() && ApiConfigBean.REQUEST_NAME_SUCCESS_VALUE_OK.equals(responseJson.getString(ApiConfigBean.RESPONSE_NAME_STATUS))) {
                    result.put("id", UUID.randomUUID().toString());
                    result.put("type", ApiConfigBean.REQUEST_TYPE_IPV4);
                    result.put("value", ip);
                    result.put("data_source", new String[]{DNS_REQUEST_URL + ip});

                    //国家名称
                    if (responseJson.containsKey("country") && StringUtils.isNotBlank("country")) {
                        result.put("country_en_name", responseJson.getString("country"));
                    }

                    //城市名称
                    if (responseJson.containsKey("city") && StringUtils.isNotBlank("city")) {
                        result.put("city_en_name", responseJson.getString("city"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result.toJSONString();
        }
        return result.toJSONString();
    }
}
