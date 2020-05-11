package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.service.FreegeoipService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class FreegeoipServiceImpl implements FreegeoipService {
    @Override
    public String getIPMessage(String value) {

        try {
            String gbk = HttpClientUtil.sendGetData("https://freegeoip.app/json/" + value,"UTF-8");
            JSONObject jsonObject = JSON.parseObject(gbk);
            Map<String, Object> ipMap =  new HashMap<>();
            ipMap.put("type","IPV4");
            ipMap.put("value",value);
            if(jsonObject.containsKey("country_code")){
                ipMap.put("country_ios_code",jsonObject.getString("country_code"));
            }
            if(jsonObject.containsKey("country_name")){
                ipMap.put("country_en_name",jsonObject.getString("country_name"));
            }
            if(jsonObject.containsKey("region_name")){
                ipMap.put("province_en_name",jsonObject.getString("region_name"));
            }
            if(jsonObject.containsKey("region_code")){
                ipMap.put("province_ios_code",jsonObject.getString("region_code"));
            }
            if(jsonObject.containsKey("latitude")){
                ipMap.put("latitude",jsonObject.getString("latitude"));
            }
            if(jsonObject.containsKey("longitude")){
                ipMap.put("longitude",jsonObject.getString("longitude"));
            }
            ipMap.put("data_source", Arrays.asList("https://freegeoip.app"));

            return JSON.toJSONString(ipMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
