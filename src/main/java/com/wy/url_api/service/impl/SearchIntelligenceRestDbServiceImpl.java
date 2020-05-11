package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.domain.ApiConfigBean;
import com.wy.url_api.service.SearchIntelligenceIocRestDbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by M on 2020/2/11.
 */
@Service
public class SearchIntelligenceRestDbServiceImpl implements SearchIntelligenceIocRestDbService {

    private static final String REQUEST_URL = "https://rest.db.ripe.net/search.json?source=RIPE&source=apnic-grs&query-string=";


    @Override
    public String findIntelligenceIocIpInfo(String ip) {
        JSONObject result = new JSONObject();
        try {
            if (StringUtils.isEmpty(ip)) {
                return result.toString();
            }
            String response = HttpClientUtil.sendGetData(REQUEST_URL + ip, ApiConfigBean.URL_ENCODE_UTF_NAME);
            if (StringUtils.isNoneEmpty(response)) {
                JSONObject responseJson = JSON.parseObject(response);
                if (!responseJson.isEmpty() && !responseJson.containsKey("errormessages") && responseJson.containsKey("objects")) {
                    result.put("id", UUID.randomUUID().toString());
                    result.put("type", ApiConfigBean.REQUEST_TYPE_IPV4);
                    result.put("value", ip);
                    result.put("data_source", new String[]{REQUEST_URL + ip});


                    //创建时间  修改时间
                    JSONArray objects = responseJson.getJSONObject("objects").getJSONArray("object");
                    for (Object object : objects) {
                        JSONObject messageObj = JSON.parseObject(object.toString());
                        if ("role".equals(messageObj.getString("type"))) {
                            JSONArray attributesArray = messageObj.getJSONObject("attributes").getJSONArray("attribute");
                            for (Object attributes : attributesArray) {
                                JSONObject attribute = JSON.parseObject(attributes.toString());
                                if ("created".equals(attribute.getString("name"))) {
                                    String createDateStr = attribute.getString("value");
                                    String createDate = formatDate(createDateStr);
                                    if (StringUtils.isNoneBlank(createDate)) {
                                        result.put("create_time", createDate);
                                        continue;
                                    }
                                }
                                if ("last-modified".equals(attribute.getString("name"))) {
                                    String updateDateStr = attribute.getString("value");
                                    String updateDate = formatDate(updateDateStr);
                                    if (StringUtils.isNoneBlank(updateDate)) {
                                        result.put("update_time", updateDate);
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result.toJSONString();
        }
        return result.toJSONString();
    }

    @Override
    public String findIntelligenceDomainInfo(String domain) {
        return null;
    }


    public String formatDate(String dateStr) {
        String resultDate = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = simpleDateFormat.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            resultDate = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return resultDate;
        }
        return resultDate;
    }
}
