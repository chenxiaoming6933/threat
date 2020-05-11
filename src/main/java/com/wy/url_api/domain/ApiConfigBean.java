package com.wy.url_api.domain;

import java.io.Serializable;

/**
 * Created by M on 2020/2/13.
 * API 相关字典
 * 可自定义属性 方法
 * 请求URL 不建议在这里定义
 */
public class ApiConfigBean implements Serializable {

    /**
     * api 查询类型 value
     * URL、IPV4、DOMAIN、EMAIL、MD5、SHA1、SHA256、OTHERe
     */
    public static final String REQUEST_TYPE_URL = "URL";

    public static final String REQUEST_TYPE_IPV4 = "IPV4";

    public static final String REQUEST_TYPE_DOMAIN = "DOMAIN";

    public static final String REQUEST_TYPE_EMAIL = "EMAIL";

    public static final String REQUEST_TYPE_MD5 = "MD5";

    public static final String REQUEST_TYPE_SHA1 = "SHA1";

    public static final String REQUEST_TYPE_SHA256 = "SHA256";

    public static final String REQUEST_TYPE_OTHER = "OTHER";

    public static final String REQUEST_TYPE_DNS = "DNS";

    /**
     * http  基础常量
     */
    public static final String RESPONSE_NAME_STATUS = "status";

    public static final String REQUEST_NAME_SUCCESS_VALUE_OK = "ok";

    public static final String URL_ENCODE_UTF_NAME = "utf-8";


}
