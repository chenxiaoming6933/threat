package com.wy.url_api.service;

/**
 * Created by M on 2020/2/11.
 * 威胁情报  http://rest.db.ripe.net/  网址 api接口
 */
public interface SearchIntelligenceIocRestDbService {

    /**
     * 获取 IP 类型数据
     * 返回结构体为  情报IOC类型
     * @param ip
     * @return
     */
    String findIntelligenceIocIpInfo(String ip);

    /**
     * 获取域名 类型数据
     * 返回结构体为  情报IOC类型
     * @param domain
     * @return
     */
    String findIntelligenceDomainInfo(String domain);
}
