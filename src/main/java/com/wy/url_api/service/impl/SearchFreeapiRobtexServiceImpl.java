package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.service.SearchFreeapiRobtexService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.robtex.com/api/#free_api提供api
 */
@Service
public class SearchFreeapiRobtexServiceImpl implements SearchFreeapiRobtexService {
    /**
     * ip查询
     * 测试api：https://freeapi.robtex.com/ipquery/121.19.61.60
     * @param ip
     * @return
     */
    @Override
    public String findFreeapiRobtexByIp(String ip) {
        String url = "https://freeapi.robtex.com/ipquery/"+ip;
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status").toString();
            Map<String,Object> map = new HashMap<>();
            if("ok".equals(status)) {
                map.put("type", "IPV4");
                map.put("value", ip);
                map.put("country_en_name", obj.get("country"));
                map.put("province_en_name", obj.get("city"));
                map.put("isp", obj.get("whoisdesc"));
                map.put("data_source", Arrays.asList("https://www.robtex.com/api/#free_api"));
                //以下字段是否需要待定
               /* map.put("act", obj.get("act"));//活动（转发）DNS
                map.put("acth", obj.get("acth"));//活动（转发）DNS历史记录
                map.put("pas", obj.get("pas"));// 被动（反向）DNS
                map.put("pash", obj.get("pash"));// 被动（反向）DNS历史记录*/
            }
            result = JSON.toJSONString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
