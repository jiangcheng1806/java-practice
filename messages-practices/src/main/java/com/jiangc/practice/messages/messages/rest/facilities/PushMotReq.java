package com.jiangc.practice.messages.messages.rest.facilities;

import java.io.Serializable;

import lombok.Data;

@Data
public class PushMotReq implements Serializable {
    private static final long serialVersionUID = -5625143119909647963L;

    private Long id;
    private String infCode;
    private String pushContent;
    private Integer systemSource;
}