package com.jiangcz.application.redis.data0;

import lombok.Data;

@Data
public class TestRecordVo {
    /**
     * 编号
     */
    private Long Id;
    /**
     * 考核通过有效期（结束日期）
     */
    private String authEndDate;
    /**
     * 考核通过有效期（开始日期）
     */
    private String authStartDate;
    /**
     * 司机
     */
    private String authType;
    /**
     * 手机号
     */
    private String cellPhone;
    /**
     * 创建日期
     */
    private String createTime;
    /**
     * 考试分数
     */
    private String examScore;
    /**
     * 身份证号
     */
    private String identityCardId;
    /**
     * 职位
     */
    private String jobTitle;
    /**
     * 用户编号
     */
    private String memberId;
    /**
     * 及格线
     */
    private String passLine;
    /**
     * 聚烯烃
     */
    private String productName;
    /**
     * 聚烯烃
     */
    private String productType;
    /**
     * 王刚
     */
    private String reallyName;
    /**
     * 所在公司名称
     */
    private String teamName;
    /**
     * 试卷总分
     */
    private String totalScore;
}
