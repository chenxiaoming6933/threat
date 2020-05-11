package com.wy.url_api.service;

/**
 * https://www.threatminer.org/api.php提供接口
 */
public interface SearchThreatminerApiService {

    //ipWHOIS查询
    public String findThreatminerWhoisByIp(String ip);
    //PassiveDNS查询（RDNS）
    public String findPassiveDNSByIp(String ip);
    //Subdomains查询
    public String findSubdomains(String domain);
    //样本关联ip和域名
    public String findSamplesIPDomains(String samples);
    //ip关联样本
    public String findRelatedSamplesByIp(String ip);
    //PassiveDNS查询（FDNS）
    public String findPassiveDNSByDomain(String domain);
    //Email (Reverse WHOIS)
    public String findDomainByEmail(String email);
}
