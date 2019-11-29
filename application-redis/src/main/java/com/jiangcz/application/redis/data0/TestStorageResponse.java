package com.jiangcz.application.redis.data0;

import lombok.Data;

@Data
public class TestStorageResponse {

    private String totalcount;

    private String status;

    private String resultmsg;

    private Object data;
}
