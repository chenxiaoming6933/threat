package com.wy.url_api.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M on 2020/2/13.
 * 情报IOC 实体类
 */
public class IntelligenceIocBean implements Serializable {

    /**
     * 主键 UUID
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty})
    private String id;

    /**
     * 类型
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty})
    private String type;

    /**
     * 参数值
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty})
    private String value;

    /**
     * 国家国际编码
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "country_iso_code")
    private String countryIsoCode;

    /**
     * 国家英文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "country_en_name")
    private String countryEnName;

    /**
     * 国家中文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "country_cn_name")
    private String countryCnName;


    /**
     * 省份国际编码
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "province_iso_code")
    private String provinceIsoCode;

    /**
     * 省份英文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "province_en_name")
    private String provinceEnName;

    /**
     * 省份中文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "province_cn_name")
    private String provinceCnName;


    /**
     * 城市国际编码
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "city_iso_code")
    private String cityIsoCode;

    /**
     * 城市英文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "city_en_name")
    private String cityEnName;

    /**
     * 城市中文名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "city_cn_name")
    private String cityCnName;


    /**
     * 纬度
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Double latitude;


    /**
     * 经度
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Double longitude;

    /**
     * 服务商
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty})
    private String isp;


    /**
     * 首次活动时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "first_active_time")
    private String firstActiveTime;

    /**
     * 最后活动时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "last_active_time")
    private String lastActiveTime;

    /**
     * 攻击行为
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "attack_action")
    private List<String> attackAction;


    /**
     * 攻击协议
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "attack_protocol")
    private List<String> attackProtocol;

    /**
     * 智能分析
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty})
    private  List<intelligenceIocAnalysisBean> intelligence;


    /**
     * 系统标签
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "system_label")
    private List<String> systemLabel;

    /**
     * 分析标签
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "analyse_label")
    private List<String> analyseLabel;

    /**
     * 用户标签
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "user_label")
    private List<String> userLabel;


    /**
     * 数据来源
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "data_source")
    private List<String> dataSource;

    /**
     * 删除状态
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue}, name = "is_delete")
    private Integer isDelete;


    /**
     * 创建时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "create_time")
    private String createTime;

    /**
     * 创建人
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "create_user")
    private String createUser;

    /**
     * 修改时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "update_time")
    private String updateTime;

    /**
     * 修改人
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "update_user")
    private String updateUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryEnName() {
        return countryEnName;
    }

    public void setCountryEnName(String countryEnName) {
        this.countryEnName = countryEnName;
    }

    public String getCountryCnName() {
        return countryCnName;
    }

    public void setCountryCnName(String countryCnName) {
        this.countryCnName = countryCnName;
    }

    public String getProvinceIsoCode() {
        return provinceIsoCode;
    }

    public void setProvinceIsoCode(String provinceIsoCode) {
        this.provinceIsoCode = provinceIsoCode;
    }

    public String getProvinceEnName() {
        return provinceEnName;
    }

    public void setProvinceEnName(String provinceEnName) {
        this.provinceEnName = provinceEnName;
    }

    public String getProvinceCnName() {
        return provinceCnName;
    }

    public void setProvinceCnName(String provinceCnName) {
        this.provinceCnName = provinceCnName;
    }

    public String getCityIsoCode() {
        return cityIsoCode;
    }

    public void setCityIsoCode(String cityIsoCode) {
        this.cityIsoCode = cityIsoCode;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getCityCnName() {
        return cityCnName;
    }

    public void setCityCnName(String cityCnName) {
        this.cityCnName = cityCnName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getFirstActiveTime() {
        return firstActiveTime;
    }

    public void setFirstActiveTime(String firstActiveTime) {
        this.firstActiveTime = firstActiveTime;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public List<String> getAttackAction() {
        return attackAction;
    }

    public void setAttackAction(List<String> attackAction) {
        this.attackAction = attackAction;
    }

    public List<String> getAttackProtocol() {
        return attackProtocol;
    }

    public void setAttackProtocol(List<String> attackProtocol) {
        this.attackProtocol = attackProtocol;
    }

    public List<String> getSystemLabel() {
        return systemLabel;
    }

    public void setSystemLabel(List<String> systemLabel) {
        this.systemLabel = systemLabel;
    }

    public List<String> getAnalyseLabel() {
        return analyseLabel;
    }

    public void setAnalyseLabel(List<String> analyseLabel) {
        this.analyseLabel = analyseLabel;
    }

    public List<String> getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(List<String> userLabel) {
        this.userLabel = userLabel;
    }

    public List<String> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public List<intelligenceIocAnalysisBean> getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(List<intelligenceIocAnalysisBean> intelligence) {
        this.intelligence = intelligence;
    }
}
