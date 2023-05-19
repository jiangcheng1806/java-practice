package com.jiangc.practice.messages.messages.rest;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author huangsongtao
 * @description
 * @date 2022/7/21 17:02
 */
@Slf4j
public class Base64Util {
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    public static String encode(byte[] data) {
        try {
            return ENCODER.encodeToString(data);
        } catch (Exception e) {
            log.error("base64加密异常 : {}", data);
            return null;
        }
    }

    public static String encode(String data) {
        try {
            return ENCODER.encodeToString(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("base64加密异常 : {}", data);
            return null;
        }
    }

    public static byte[] encodeToByte(String data) {
        try {
            return ENCODER.encode(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("base64加密异常 : {}", data);
            return null;
        }
    }

    public static String decode(byte[] encodeData) {
        try {
            return new String(encodeData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("base64解密异常 : {}", encodeData);
            return null;
        }
    }

    public static String decode(String encodeData) {
        try {
            return new String(DECODER.decode(encodeData), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("base64解密异常 : {}", encodeData);
            return null;
        }
    }

    public static byte[] decodeToByte(String encodeData) {
        try {
            return DECODER.decode(encodeData);
        } catch (Exception e) {
            log.error("base64解密异常 : {}", encodeData);
            return null;
        }
    }

}
