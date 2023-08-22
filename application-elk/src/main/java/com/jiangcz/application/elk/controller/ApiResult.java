package com.jiangcz.application.elk.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Data
public class ApiResult {
    private Integer status;

    private String code;

    private String msg;
}