package com.wy.url_api.service;


import com.wy.url_api.domain.ApiResponseBean;

/**
 * Created by M on 2020/2/13.
 * 情报IOC类型 不同类型 获取不同数据
 */
public interface IntelligenceIocService {

    /**
     * 获取URL 类型  所有数据
     * @param value
     * @return
     */
    ApiResponseBean findUrlInfo(String value,String type);

    /**
     * 获取IP 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findIpInfo(String value,String type);

    /**
     * 获取DOMAIN 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findDomainInfo(String value,String type);

    /**
     * 获取EMAIL 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findEmailInfo(String value,String type);


    /**
     * 获取MD5类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findMd5Info(String value,String type);

    /**
     * 获取SHA1 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findSha1Info(String value,String type);

    /**
     * 获取SHA256 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findSha256Info(String value,String type);


    /**
     * 获取OTHER 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findOtherInfo(String value,String type);

    /**
     * 获取DNS 类型 所有数据
     * @param value
     * @return
     */
    ApiResponseBean findDnsInfo(String value,String type);
}
