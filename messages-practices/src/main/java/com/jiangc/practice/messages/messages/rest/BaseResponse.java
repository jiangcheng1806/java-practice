package com.jiangc.practice.messages.messages.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseResponse<T> implements Serializable {
    @JsonProperty("data")
    private final T data;
    @JsonProperty("status")
    private final String status;
    @JsonProperty("message")
    private final String message;

    public static Builder successCustom() {
        return successCustom("操作成功！");
    }

    public static Builder successCustom(String message) {
        return new Builder("0000", message);
    }

    public static Builder successNonDataOutCustom(String message) {
        return new Builder("1000", message);
    }

    public static Builder failedCustom(String errorCode, String errorMsg) {
        if (StringUtils.isBlank(errorCode)) {
            failedCustom(errorMsg);
        }

        return new Builder(errorCode, errorMsg);
    }

    public static Builder failedCustom(String errorMsg) {
        return new Builder("4000", errorMsg);
    }

    public static Builder systemErrorCustom(String errorMsg) {
        return new Builder("4009", errorMsg);
    }

    public static final class Builder {
        private final String status;
        private final String msg;
        private Object data;

        private Builder(String status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Builder setObj(Object data) {
            this.data = data;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this.data == null ? "" : this.data, this.status, this.msg);
        }
    }
}
