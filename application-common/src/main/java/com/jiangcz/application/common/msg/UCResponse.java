package com.jiangcz.application.common.msg;

import com.jiangcz.application.common.constant.CodeStatus;
import com.jiangcz.application.common.constant.UCHttpRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class UCResponse implements Serializable {

    private static final long serialVersionUID = -7305584331139360552L;
    private Integer code;

    private Integer status;

    private String msg;

    private Object data;

    private Object datas;

    private Object body;

    public UCResponse(CodeStatus codeStatus){
        this.code = UCHttpRequest.UC_SYSTEM_ERROR;
        this.msg = codeStatus.getName();
    }
    public UCResponse(Object result){
        this.code = UCHttpRequest.UC_HTTP_SUCCESS;
        this.data = result;
    }
    public UCResponse(){
        this.code = UCHttpRequest.UC_HTTP_SUCCESS;
    }
	@Override
	public String toString() {
		return "UCResponse [code=" + code + ", status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
    
    
    
}
