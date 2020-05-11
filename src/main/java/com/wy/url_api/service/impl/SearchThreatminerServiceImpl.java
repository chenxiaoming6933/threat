package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.HttpClientUtil;
import com.wy.url_api.service.SearchThreatminerApiService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * https://www.threatminer.org/api.php对外接口
 * 请注意，该api速率限制设置为每分钟10个查询。
 */
@Service
public class SearchThreatminerServiceImpl implements SearchThreatminerApiService {

    /**
     * whois类型
     * 测试api：https://api.threatminer.org/v2/host.php?q=216.58.213.110&rt=1
     * @param ip
     * @return
     */
    @Override
    public String findThreatminerWhoisByIp(String ip) {
        String url = "https://api.threatminer.org/v2/host.php?q="+ip+"&rt=1";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray ipInfoArry = obj.getJSONArray("results");
                if(ipInfoArry!=null&&ipInfoArry.size()>0){
                    JSONObject objnow =  (JSONObject)ipInfoArry.get(0);
                    map.put("type", "IPV4");
                    map.put("value", ip);
                    map.put("country_iso_code", obj.get("cc"));
                    map.put("province_en_name", obj.get("city"));
                    map.put("isp", obj.get("org_name"));
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    //以下字段是否需要待定
                    /*map.put("asn", obj.get("asn"));
                    map.put("reverse_name", obj.get("reverse_name"));
                    map.put("bgp_prefix", obj.get("bgp_prefix"));
                    map.put("asn", obj.get("asn"));
                    map.put("asn_name", obj.get("asn_name"));*/
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Passive DNS类型（RDNS）
     * 测试api：https://api.threatminer.org/v2/host.php?q=216.58.213.110&rt=2
     * @param ip
     * @return
     */
    @Override
    public String findPassiveDNSByIp(String ip) {
        String url = "https://api.threatminer.org/v2/host.php?q="+ip+"&rt=2";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray domainsInfoArry = obj.getJSONArray("results");
                if(domainsInfoArry!=null&&domainsInfoArry.size()>0){
                    map.put("type", "IPV4");
                    map.put("value", ip);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    Map<String,Object> intelligenceMap = new HashMap<>();
                    intelligenceMap.put("category","RDNS");//Passive DNS
                    intelligenceMap.put("desc",domainsInfoArry);
                    intelligenceList.add(intelligenceMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Related Samples (Hash only)  ip关联样本
     * 测试api：https://api.threatminer.org/v2/host.php?q=216.58.213.110&rt=4
     * @param ip
     * @return
     */
    @Override
    public String findRelatedSamplesByIp(String ip) {
        String url = "https://api.threatminer.org/v2/host.php?q="+ip+"&rt=4";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray ipInfoArry = obj.getJSONArray("results");
                if(ipInfoArry!=null&&ipInfoArry.size()>0){
                    // JSONObject objnow =  (JSONObject)ipInfoArry.get(0);
                    map.put("type", "IPV4");
                    map.put("value", ip);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    Map<String,Object> intelligenceMap = new HashMap<>();
                    intelligenceMap.put("category","Related Samples");//关联样本
                    intelligenceMap.put("desc",ipInfoArry);
                    intelligenceList.add(intelligenceMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Subdomains类型
     * 测试api：https://api.threatminer.org/v2/domain.php?q=vwrm.com&rt=5
     * @param domain
     * @return
     */
    @Override
    public String findSubdomains(String domain) {
        String url = "https://api.threatminer.org/v2/domain.php?q="+domain+"&rt=5";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray ipInfoArry = obj.getJSONArray("results");
                if(ipInfoArry!=null&&ipInfoArry.size()>0){
                    // JSONObject objnow =  (JSONObject)ipInfoArry.get(0);
                    map.put("type", "DOMAIN");
                    map.put("value", domain);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    Map<String,Object> intelligenceMap = new HashMap<>();
                    intelligenceMap.put("category","Subdomains");//子域名
                    intelligenceMap.put("desc",ipInfoArry);
                    intelligenceList.add(intelligenceMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 样本关联ip和域名
     * 测试api：https://api.threatminer.org/v2/sample.php?q=e6ff1bf0821f00384cdd25efb9b1cc09&rt=3
     * @param samples
     * @return
     */
    @Override
    public String findSamplesIPDomains(String samples) {
        String url = "https://api.threatminer.org/v2/sample.php?q="+samples+"&rt=3";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray ipInfoArry = obj.getJSONArray("results");
                if(ipInfoArry!=null&&ipInfoArry.size()>0){
                    JSONObject objnow =  (JSONObject)ipInfoArry.get(0);
                    map.put("type", "Samples");//样本关联IP和域名 type=Samples？？or OTHER？？ or ？？
                    map.put("value", samples);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    //关联域名
                    Map<String,Object> domainsMap = new HashMap<>();
                    domainsMap.put("category","domains");
                    domainsMap.put("desc",objnow.get("domains"));
                    intelligenceList.add(domainsMap);
                    //关联ip
                    Map<String,Object> hostMap = new HashMap<>();
                    hostMap.put("category","hosts");
                    hostMap.put("desc",objnow.get("hosts"));
                    intelligenceList.add(hostMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Passive DNS类型（FDNS）
     * 测试api：https://api.threatminer.org/v2/domain.php?q=vwrm.com&rt=2
     * @param domain
     * @return
     */
    @Override
    public String findPassiveDNSByDomain(String domain){
        String url = "https://api.threatminer.org/v2/domain.php?q="+domain+"&rt=2";
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray ipsInfoArry = obj.getJSONArray("results");
                if(ipsInfoArry!=null&&ipsInfoArry.size()>0){
                    map.put("type", "DOMAIN");
                    map.put("value", domain);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    Map<String,Object> intelligenceMap = new HashMap<>();
                    intelligenceMap.put("category","FDNS");//Passive DNS
                    intelligenceMap.put("desc",ipsInfoArry);
                    intelligenceList.add(intelligenceMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Email (Reverse WHOIS)
     * 测试api：https://api.threatminer.org/v2/email.php?q=7bf5721bfa009479c33f3c3cf4ea5392200f030e
     * @param email  例：dns-admin@google.com
     * @return
     */
    @Override
    public String findDomainByEmail(String email){
        if(email.contains("@")) {
            email = DigestUtils.sha1Hex(email.trim());//sha1加密后作为参数传递
        }
        String url = "https://api.threatminer.org/v2/email.php?q="+email;
        String result ="";
        try {
            String data =HttpClientUtil.sendGetData(url,"UTF-8");
            JSONObject obj = JSON.parseObject(data);
            String status = obj.get("status_code").toString();
            if("200".equals(status)) {
                Map<String,Object> map = new HashMap<>();
                JSONArray domainsInfoArry = obj.getJSONArray("results");
                if(domainsInfoArry!=null&&domainsInfoArry.size()>0){
                    map.put("type", "EMAIL");
                    map.put("value", email);
                    map.put("data_source", Arrays.asList("https://www.threatminer.org/api.php"));
                    List<Map<String,Object>> intelligenceList = new ArrayList<>();
                    Map<String,Object> intelligenceMap = new HashMap<>();
                    intelligenceMap.put("category","domain");//domain
                    intelligenceMap.put("desc",domainsInfoArry);
                    intelligenceList.add(intelligenceMap);
                    map.put("intelligence",intelligenceList);
                }
                result = JSON.toJSONString(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
