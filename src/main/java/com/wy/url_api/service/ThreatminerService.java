package com.wy.url_api.service;

public interface ThreatminerService {

    String getDomainWhois(String value);

    String getImportHash(String value);

    String getDomainDns(String value);

    String getDomainURL(String value);

    String getSonDomain(String value);
}
