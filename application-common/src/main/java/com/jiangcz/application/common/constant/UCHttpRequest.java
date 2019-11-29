package com.jiangcz.application.common.constant;


public class UCHttpRequest {

    /**
     * 普通post请求
     */
    public static final String UC_HTTP_POST = "POST";
    
    /**
     * 普通put请求
     */
    public static final String UC_HTTP_PUT = "PUT";

    /**
     * 普通get请求
     */
    public static final String UC_HTTP_GET = "GET";

    /**
     * rest get请求
     */
    public static final String UC_HTTP_RESTGET = "RESTGET";

    /**
     *  uc 返回code success
     */
    public static final Integer UC_HTTP_SUCCESS = 10102000;

    /**
     * 数据库中不存在数据（用户信息不存在或未绑定企业）
     */
    public static final Integer UC_COMPANY_NOT_EXIST = 10104090;

    /**
     * 系统错误
     */
    public static final Integer UC_SYSTEM_ERROR = 10105001;

    /**
     * 参数格式错误
     */
    public static final Integer UC_PARAM_FORMAT_ERROR = 10104012;

    /**
     * 缺少参数
     */
    public static final Integer UC_PARAM_MISS = 10104011;

    /**
     * 用户数据缺失或请求参数错误/用户资料没有修改
     */
    public static final Integer UC_REQUEST_FAIL = 10104000;

    /**
     * 无效的token
     */
    public static final Integer UC_LOGIN_OVERDUE = 10104023;


}
