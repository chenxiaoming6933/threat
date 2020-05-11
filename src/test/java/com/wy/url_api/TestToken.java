package com.wy.url_api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wy.url_api.common.util.AESUtil;
import com.wy.url_api.common.util.DateUtil;
import com.wy.url_api.common.util.MD5;
import org.junit.Test;

import java.util.Date;

/**
 * Created by M on 2020/4/1.
 */
public class TestToken {

    @Test
    public  void  testTokenStr(){
        String  md5Str= MD5.createPassword("intelligence"+"wangyun");
        System.out.println(md5Str);
        try {
            JSONObject object = new JSONObject();
            object.put("type", "IPV4");
            object.put("value", "192.168.1.231");
            object.put("authorized", md5Str);
            object.put("date", DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));

            String encrypt = AESUtil.getEncryptData(md5Str, object.toJSONString());
            String decryptData = AESUtil.getDecryptData(md5Str, encrypt);
            JSONObject jsonObject = JSON.parseObject(decryptData);
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
