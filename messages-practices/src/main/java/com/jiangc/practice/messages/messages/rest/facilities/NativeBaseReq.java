package com.jiangc.practice.messages.messages.rest.facilities;

import java.io.Serializable;
/**
 * 包装非分页参数
 * */
public class NativeBaseReq<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务入参对象
     */
    private T in ;

    /**
     * 业务请求时间
     */
    private String timeStamp;

    /**
     * 调用方
     */
    private String sourceSysCode;

    public NativeBaseReq(T in, String timeStamp, String sourceSysCode) {
        this.in = in;
        this.timeStamp = timeStamp;
        this.sourceSysCode = sourceSysCode;
    }

    public NativeBaseReq(String timeStamp, String sourceSysCode, T in){
        this.in = in;

    }

    public T getIn() {
        return in;
    }

    public void setIn(T in) {
        this.in = in;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSourceSysCode() {
        return sourceSysCode;
    }

    public void setSourceSysCode(String sourceSysCode) {
        this.sourceSysCode = sourceSysCode;
    }

    public static <T> NativeBaseReq<T> putIn(T in){
        return new NativeBaseReq<>("","",in);
    }
}
