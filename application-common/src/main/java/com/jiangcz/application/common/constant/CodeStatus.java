package com.jiangcz.application.common.constant;

public enum CodeStatus {

    //成功
    CODE_SUCCESS(20000, "请求成功"),


    /**
     * 服务错误,10***
     * 服务器内部错误(自定义的异常)
     */

    SERVICE_UNKNOWNERROR(10000, "未知原因"),
    SERVICE_UNAVAILABLE(10001, "服务不可用"),
    SERVICE_INTERNELEXCEPTION(10002, "加载失败，请稍后再试或联系客服"),
    SERVICE_TOOBUSY(10003, "服务繁忙"),
    SERVICE_TIMEOUT(10004, "服务超时"),
    SERVICE_NOTFOUND(10005, "服务不存在"),
    SERVICE_UNSUPPORTCALL(10006, "不支持的调用方式,请参考API文档"),
    SERVICE_DEPRECATED(10007, "服务已弃用"),
    PARAM_ACCESS_FORBIDDEN(10008, "权限等级不够"),

    /**
     * 参数错误,11***
     * 请求参数错误（对GET和POST请求中携带的参数的校验结果）
     */

    //API文档相关,110**
    PARAM_ERROR(11000, "请参考API文档"),
    PARAM_INVALID(11001, "非法参数(),请参考API文档"),
    PARAM_UNSUPPORTMEDIATYPE(11002, "不支持的MEDIATYPE(%S)"),
    PARAM_INVALIDFORMAT(11003, "无效数据格式,请参考API文档"),
    PARAM_INVALIDENCODE(11004, "编码错误,请参考API文档"),
    PARAM_FILEERROR(11005, "暂不支持该格式的文件上传,请您重新上传"),
    PARAM_ERROR_FOR_USER(11006, "提交的数据有误，请检查重新提交"),

    //缺少参数,111**
    PARAM_NEEDAPPKEY(11100, "缺少APPKEY"),
    PARAM_NEEDAPIKEY(11101, "缺少APIKEY"),
    PARAM_NEEDSIGN(11102, "缺少SIGN"),
    PARAM_NEEDTIMESTAMP(11103, "缺少时间戳TIMESTAMP"),
    PARAM_REQUIRED_MISS(11104, "缺少必选参数,请参考API文档"),
    PARAM_REQUIRED_FAILURE(11105, "token缺失或失效"),
    PARAM_REQUIRED_UPDATE(11106, "请重启并更新当前APP"),


    //无效参数,112**
    PARAM_INVALIDAPPKEY(11200, "无效APPKEY"),
    PARAM_INVALIDAPIKEY(11201, "无效APIKEY"),
    PARAM_INVALIDSIGN(11202, "无效签名,请参考API文档"),
    PARAM_INVALIDTIMESTAMP(11203, "无效时间戳,请参考API文档"),
    PARAM_INVALIDMOBILE(11204, "该手机号未在本平台注册，请联系本人尽快注册，再进行后续操作！"),
    PARAM_INVALIDCAPTCHA(11205, "无效的图片验证码"),
    PARAM_INVALIDSSMCAPTCHA(11206, "无效的短信验证码"),
    PARAM_INVALIDUSER(11207, "用户不存在"),
    PARAM_EXISTUSER(11208, "用户已存在"),
    PARAM_DIFFERENTMOBILE(11209, "注册的手机号和发送验证码的手机号不一致"),

    /**
     * 权限安全,12***
     */
    ACCESS_FORBIDDEN(12000, "非法访问"),
    ACCESS_IPFORBIDDEN(12001, "IP访问受限"),
    ACCESS_APPFORBIDDEN(12002, "APP访问受限"),
    ACCESS_APPOVERQUOTA(12003, "APP访问配额超限"),
    ACCESS_APPTOOFREQUENTLY(12004, "APP访问过于频繁"),
    ACCESS_SESSIONFORBIDDEN(12005, "会话访问受限"),
    ACCESS_SESSIONOVERQUOTA(12006, "会话访问配额超限"),
    ACCESS_SESSIONTOOFREQUENTLY(12007, "会话访问过于频繁"),
    ACCESS_USERINACTIVE(12008, "用户未启用,请联系管理员"),

    ACCESS_USERAUTH_DEFICIENCY(12101, "权限不足"),

    COMMUNICATION_FAILED_NETWORK(13001, "访问远程服务失败"),
    COMMUNICATION_ERROR_RESPONSE(13002, "远程服务异常"),


    NO_DATA_IN_CACHE(14020, "缓存中无数据"),

    /**
     * 短信相关
     */
    SSM_SUCCESS_SEND(200, "短信发送成功"),
    SSM_FAILED_IPFORBIDDEN(315, "IP限制"),
    SSM_FAILED_INVALIDUSER(404, "对象不存在"),
    SSM_FAILED_VALIDATEERROR(413, "验证失败(短信服务)"),
    SSM_FAILED_PARAMERROR(414, "参数错误"),
    SSM_FAILED_OVEROPERATE(416, "频率控制,今日发送次数超上限")
    ;

    private int value;
    private String name;

    CodeStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    /**
     * @param code 状态码
     * @return 状态枚举
     * @desc 通过状态码查询状态枚举
     */
    public static CodeStatus getEnum(int code) {
        if (code == 0) {
            return null;
        }
        for (CodeStatus codeStatus : CodeStatus.values()) {
            if (codeStatus.getValue() == code) {
                return codeStatus;
            }
        }
        return null;
    }
}
