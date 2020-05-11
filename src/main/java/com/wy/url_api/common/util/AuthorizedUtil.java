package com.wy.url_api.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by M on 2020/2/12.
 * 身份认证工具
 * 验证方式  请求参数（json）+MD5加密
 * 生成字符串后AES加密
 */
@Component
public class AuthorizedUtil {

    @Value("${authorized.appkey}")
    private String appkey;

    @Value("${authorized.appsecret}")
    private String appsecret;

    /**
     * token校验  伪token  没用jwt
     * 没做时间限制
     *
     * @return
     */
    public boolean authorizedToken(String token) {
        if (!StringUtils.isNotBlank(token)) {
            return false;
        }
        try {
            String decrypt = AESUtil.getDecryptData(MD5.createPassword(appkey + appsecret),token);
            if (!StringUtils.isNotBlank(decrypt)) {
                return false;
            }
            JSONObject requestParam = JSON.parseObject(decrypt);
            if (requestParam.isEmpty() || !requestParam.containsKey("type") || !requestParam.containsKey("value") || !requestParam.containsKey("authorized")) {
                return false;
            }
            if (!MD5.createPassword(appkey + appsecret).equals(requestParam.getString("authorized").toUpperCase())) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
