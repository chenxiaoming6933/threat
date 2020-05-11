package com.wy.url_api.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M on 2020/2/13.
 * 情报IOC结构-智能分析实体
 */
public class intelligenceIocAnalysisBean implements Serializable {

    /**
     * 分类名称
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty})
    private String category;


    /**
     * 描述列表
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private List<String> desc;


    /**
     * 来源列表
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}, name = "source_ref")
    private List<String> sourceRef;


    /**
     * 活动时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "active_time")
    private String activeTime;


    /**
     * 分数
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
    private Double score;

    /**
     * 评定时间
     */
    @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty}, name = "evaluate_time")
    private String evaluateTime;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public List<String> getSourceRef() {
        return sourceRef;
    }

    public void setSourceRef(List<String> sourceRef) {
        this.sourceRef = sourceRef;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }
}
