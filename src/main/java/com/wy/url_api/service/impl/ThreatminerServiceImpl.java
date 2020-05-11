package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.service.ThreatminerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ThreatminerServiceImpl implements ThreatminerService {
    @Override
    public String getDomainWhois(String value) {

        try {
            // rt=1 whois
            // rt=2 被动DNS
            // rt=3 可使用地址
            // rt=4 hash
            // rt=5 子域名
            // rt=6 报告标记
            String resultContent = HttpClientUtil.sendPostDataByMap("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=1", null, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(resultContent);

            Map<String, Object> domainMap =  new HashMap<>();
            domainMap.put("type","DOMAIN");
            domainMap.put("value",value);
            if(jsonObject.getString("status_code").equals("200")){
                JSONObject results = jsonObject.getJSONArray("results").getJSONObject(0);
                JSONObject whois = results.getJSONObject("whois");

                Map<String, String> reg_info = getCountry(whois.getJSONObject("reg_info"));
                if(reg_info.size()==0){
                    reg_info = getCountry(whois.getJSONObject("admin_info"));
                }
                domainMap.putAll(reg_info);

            }
            domainMap.put("data_source",Arrays.asList("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=1"));

            System.out.println(resultContent);
            return JSON.toJSONString(domainMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    // 获取城市信息
    private Map<String, String> getCountry(JSONObject countryJSON){
        Map<String, String> countryMap = new HashMap<>();
        if(StringUtils.isNotBlank(countryJSON.getString("Country"))){
            countryMap.put("country_en_name",countryJSON.getString("Country"));
        }
        if(StringUtils.isNotBlank(countryJSON.getString("City"))){
            countryMap.put("province_en_name",countryJSON.getString("City"));
        }
        return countryMap;
    }


    @Override
    public String getImportHash(String value) {

        try {
            String resultContent = HttpClientUtil.sendPostDataByMap("https://api.threatminer.org/v2/imphash.php?q="+value+"&rt=1", null, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(resultContent);

            if(jsonObject.getString("status_code").equals("200")){
                Object results = jsonObject.get("results");

                Map<String, Object> domainMap =  new HashMap<>();
                domainMap.put("attack_action",results);
                domainMap.put("data_source",Arrays.asList("https://api.threatminer.org/v2/imphash.php?q="+value+"&rt=1"));
                return JSON.toJSONString(domainMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String getDomainDns(String value) {
        try {
            String resultContent = HttpClientUtil.sendPostDataByMap("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=2", null, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(resultContent);

            if(jsonObject.getString("status_code").equals("200")){
                JSONArray results = jsonObject.getJSONArray("results");

                Map<String, Object> domainMap =  new HashMap<>();

                List<Map<String,String>> dnsList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    JSONObject jsonObject1 = results.getJSONObject(i);

                    Map<String,String> dnsMap = new HashMap<>();
                    dnsMap.put("category","Passive DNS");
                    dnsMap.put("desc",jsonObject1.getString("ip"));
                    dnsMap.put("active_time",jsonObject1.getString("first_seen") + " - " +jsonObject1.getString("last_seen"));

                    dnsList.add(dnsMap);
                }
                domainMap.put("intelligence",dnsList);
                domainMap.put("data_source",Arrays.asList("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=2"));
                return JSON.toJSONString(domainMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String getDomainURL(String value) {
        try {
            String resultContent = HttpClientUtil.sendPostDataByMap("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=3", null, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(resultContent);

            if(jsonObject.getString("status_code").equals("200")){
                JSONArray results = jsonObject.getJSONArray("results");

                Map<String, Object> domainMap =  new HashMap<>();

                List<Map<String,Object>> dnsList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    JSONObject jsonObject1 = results.getJSONObject(i);

                    Map<String,Object> dnsMap = new HashMap<>();
                    dnsMap.put("category","Example Query URI");
                    dnsMap.put("desc",Arrays.asList(jsonObject1.getString("uri"),jsonObject1.getString("ip")));
                    dnsMap.put("active_time",jsonObject1.getString("last_seen"));

                    dnsList.add(dnsMap);
                }
                domainMap.put("intelligence",dnsList);
                domainMap.put("data_source",Arrays.asList("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=3"));
                return JSON.toJSONString(domainMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String getSonDomain(String value) {
        try {
            String resultContent = HttpClientUtil.sendPostDataByMap("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=5", null, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(resultContent);

            if(jsonObject.getString("status_code").equals("200")){
                JSONArray results = jsonObject.getJSONArray("results");

                Map<String, Object> domainMap =  new HashMap<>();

                List<Map<String,Object>> dnsList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    String sonDomain = results.getString(i);

                    Map<String,Object> dnsMap = new HashMap<>();
                    dnsMap.put("category","Subdomains");
                    dnsMap.put("desc",sonDomain);

                    dnsList.add(dnsMap);
                }
                domainMap.put("intelligence",dnsList);
                domainMap.put("data_source",Arrays.asList("https://api.threatminer.org/v2/domain.php?q="+value+"&rt=5"));
                return JSON.toJSONString(domainMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
