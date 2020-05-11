package com.wy.url_api.service;

/**
 * Created by M on 2020/2/11.
 * 威胁情报  https://freeapi.robtex.com/api/  网址 api接口
 */
public interface SearchIntelligenceIocRobtexService {


    /**
     * 获取域名 类型数据
     * 返回结构体为  情报IOC类型
     *
     * @param domain
     * @return
     */
    String findIntelligenceDomainInfo(String domain);


    /**
     * 获取反向dns  类型数据
     * 返回结构体为 情报IOC类型
     * @param ip  根据IP 查询dns信息
     * @return
     */
    String findIntelligenceReverseDnsInfo(String ip);


}
