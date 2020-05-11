package com.wy.url_api.service.impl;

import com.alibaba.fastjson.JSON;
import com.wy.url_api.domain.ApiResponseBean;
import com.wy.url_api.domain.IntelligenceIocBean;
import com.wy.url_api.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M on 2020/2/13.
 * 情报IOC 类型 接口实现类
 * 当新增某API网站 实现类时  在对应类别新增调用
 */
@Service
public class IntelligenceIocServiceImpl implements IntelligenceIocService {

    @Autowired
    private SearchIntelligenceIocRestDbService restDbService;
    @Autowired
    private SearchIntelligenceIocRobtexService iocRobtexService;

    //zyf
    @Autowired
    private SearchFreeapiRobtexService robtexService;
    @Autowired
    private SearchThreatminerApiService threatminerApiService;


    // ljd
    @Autowired
    private FreegeoipService freegeoipService;
    @Autowired
    private ThreatminerService threatminerService;

    @Override
    public ApiResponseBean findUrlInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                IntelligenceIocBean iocBean = JSON.parseObject(restDbIpInfo, IntelligenceIocBean.class);
                 String jsonBean = JSON.toJSONString(iocBean);
                Map mapBean = JSON.parseObject(jsonBean, Map.class);
                resutData.add(mapBean);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }
    
    /**
     * @Author XiaoMing
     * @description: TODO
     * @param 
     * @param: value
     * @param: type
     * @return com.wy.url_api.domain.ApiResponseBean
     * @date 2020/5/11 14:14
     * @throws 
     **/
    @Override
    public ApiResponseBean findIpInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
            String restDbIpInfo = restDbService.findIntelligenceIocIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(restDbIpInfo));
            }

            // zyf
            String freeapiRobtexByIp = robtexService.findFreeapiRobtexByIp(value);
            if (StringUtils.isNotBlank(freeapiRobtexByIp)) {
                resutData.add(formatResultData(freeapiRobtexByIp));
            }
            String passiveDNSByIp = threatminerApiService.findPassiveDNSByIp(value);//dns
            if (StringUtils.isNotBlank(passiveDNSByIp)) {
                resutData.add(formatResultData(passiveDNSByIp));
            }
            String relatedSamplesByIp =threatminerApiService.findRelatedSamplesByIp(value);//关联样本
            if (StringUtils.isNotBlank(relatedSamplesByIp)) {
                resutData.add(formatResultData(relatedSamplesByIp));
            }

            // ljd
            String freegeoip = freegeoipService.getIPMessage(value);
            if (StringUtils.isNotBlank(freegeoip)) {
                resutData.add(formatResultData(freegeoip));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findDomainInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {

             // 以此类推
            String threatminerDomainWhois = threatminerService.getDomainWhois(value);
            if (StringUtils.isNotBlank(threatminerDomainWhois)) {
                resutData.add(formatResultData(threatminerDomainWhois));
            }
            String domainDns = threatminerService.getDomainDns(value);
            if (StringUtils.isNotBlank(domainDns)) {
                resutData.add(formatResultData(domainDns));
            }
            String domainURL = threatminerService.getDomainURL(value);
            if (StringUtils.isNotBlank(domainURL)) {
                resutData.add(formatResultData(domainURL));
            }
            String donDomain = threatminerService.getSonDomain(value);
            if (StringUtils.isNotBlank(donDomain)) {
                resutData.add(formatResultData(donDomain));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findEmailInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(info));
            }
             */

            //zyf
            String domainByEmail = threatminerApiService.findDomainByEmail(value);
            if (StringUtils.isNotBlank(domainByEmail)) {
                resutData.add(formatResultData(domainByEmail));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findMd5Info(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
            String importHash = threatminerService.getImportHash(value);
            if (StringUtils.isNotBlank(importHash)) {
                resutData.add(formatResultData(importHash));
            }
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(info));
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findSha1Info(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {

            String importHash = threatminerService.getImportHash(value);
            if (StringUtils.isNotBlank(importHash)) {
                resutData.add(formatResultData(importHash));
            }
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(info));
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findSha256Info(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
            String importHash = threatminerService.getImportHash(value);
            if (StringUtils.isNotBlank(importHash)) {
                resutData.add(formatResultData(importHash));
            }
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(info));
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findOtherInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
             /*以此类推
             String info = service.findXXXApiIpInfo(value);
            if (StringUtils.isNotBlank(restDbIpInfo)) {
                resutData.add(formatResultData(info));
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    @Override
    public ApiResponseBean findDnsInfo(String value, String type) {
        List<Object> resutData = new ArrayList<>();
        try {
            String robtexDnsInfo = iocRobtexService.findIntelligenceReverseDnsInfo(value);
            if (StringUtils.isNotBlank(robtexDnsInfo)) {
                resutData.add(formatResultData(robtexDnsInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseBean(value, type, null, 500);
        }
        return new ApiResponseBean(value, type, resutData, 200);
    }

    /**
     * 格式化 返回值
     *
     * @return
     */
    private Map formatResultData(String responseJson) {
        Map resultData = new HashMap<>();
        if (!StringUtils.isNotBlank(responseJson)) {
            return resultData;
        }
        try {
            IntelligenceIocBean iocBean = JSON.parseObject(responseJson, IntelligenceIocBean.class);
            String jsonBean = JSON.toJSONString(iocBean);
            resultData = JSON.parseObject(jsonBean, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return resultData;
        }
        return resultData;
    }
}
