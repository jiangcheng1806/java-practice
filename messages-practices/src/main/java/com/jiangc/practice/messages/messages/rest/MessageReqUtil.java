package com.jiangc.practice.messages.messages.rest;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jiangc.practice.messages.messages.rest.facilities.MotSourceEnum;
import com.jiangc.practice.messages.messages.rest.facilities.NativeBaseReq;
import com.jiangc.practice.messages.messages.rest.facilities.PushMotReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

//@Slf4j
public class MessageReqUtil {

    private static String remote_rsa_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoONT1a4sD8qRnO/RbHi3s6tID2O75h7H4pnHUmZRF4b6wc9VBJlrEnFp8UUzZeS5bynp1/iQXcE5NNnS66vIb7zYWvNbtEYhcKIkNzyMhH1EO75tOTESU1wHN7ApkjqNZSDhayJhum0CGldaL5IfrvU7fgA1IRBTDgVU0Y/JL4QIDAQAB";
    private static String motPushUrl = "/api/v1/public/mot/pushMessage";

//    private static String base_url = "http://hlh-testa.chtwm.com";
//    private static String base_url = "http://hlh.haomaojf.com";
    private static String base_url = "172.20.52.207:8080";

    public static void main(String[] args) {


        PushMotReq pushMotReq = new PushMotReq();


        String content = "{\"changeDate\":\"2021-03-08 17:14:00\",\"customerName\":\"舒奥\",\"customerNo\":\"296211\",\"customerType\":1,\"dt\":\"2021-03-08\",\"newTier\":\"财富客户\",\"oldTier\":\"黑钻级会员\",\"sex\":\"1\",\"status\":\"降级\"}";
        String infCode = "inf_mot_01";
        Long id = 976L;

        pushMotReq.setPushContent(content);
        pushMotReq.setInfCode(infCode);
        pushMotReq.setSystemSource(1);
        pushMotReq.setId(id);



        NativeBaseReq<PushMotReq> input = NativeBaseReq.putIn(pushMotReq);

        String pushContent = input.getIn().getPushContent();
        String identification = input.getIn().getInfCode() + "_" + input.getIn().getId();

        MessageReqUtil.pushContentToHLH(MotSourceEnum.UNI_MEMBER_GRADE,pushContent,identification);

    }


    private static BaseResponse pushContentToHLH(MotSourceEnum motSourceEnum, String pushContent, String identification) {
        BaseResponse pushResp = null;
        try {

            String dateStr = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());

            Map<String, String> map = Maps.newHashMapWithExpectedSize(3);
            //timestamp为毫秒数的字符串形式
            map.put("timestamp", dateStr);  //值应该为毫秒数的字符串形式
//            map.put("path", "/hlh/api/public/mot/pushMessage");
            map.put("path", "/api/v1/public/mot/pushMessage");
            map.put("version", "1.0.0");
            String salt = identification;

            List<String> storedKeys = Arrays.stream(map.keySet()
                            .toArray(new String[]{}))
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
            final String sign = storedKeys.stream()
                    .map(key -> String.join("", key, map.get(key)))
                    .collect(Collectors.joining()).trim()
                    .concat(salt);
            String signMd5 = DigestUtils.md5DigestAsHex(sign.getBytes()).toUpperCase();
//            String sign = getSign(dateStr);

            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("sign", signMd5);
            headerMap.put("salt", salt);
            headerMap.put("version", map.get("version"));
            headerMap.put("timestamp", map.get("timestamp")); //值应该为毫秒数的字符串形式
            headerMap.put("path", map.get("path"));

            System.out.println("header参数=>"+JSON.toJSONString(headerMap));


            //body信息
            Map<String, String> bodyMap = new HashMap<>();
            bodyMap.put("identification", identification);
            bodyMap.put("json", pushContent);
            bodyMap.put("motSource", motSourceEnum.getType().toString());

            System.out.println("dody参数=>"+JSON.toJSONString(bodyMap));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data",RSAUtils.encryptSegmentByPublicKey(JSON.toJSONString(bodyMap),remote_rsa_key));

            String url = base_url+motPushUrl;

            System.out.println("url==>"+ url);

            //url请求
            String result = HttpRequest.post(url)
                    .addHeaders(headerMap)//头信息
                    .body(JSONObject.toJSONString(jsonObject)) //信息体
//                    .timeout(motPushTimeout)//超时，毫秒
                    .execute().body();

            System.out.println("result=>"+result);

            //返回结果不为空 转对象
            if (StringUtils.isNotEmpty(result)) {
                pushResp = JSONObject.parseObject(result, BaseResponse.class);
            }
        } catch (Exception e) {
//            log.error("HLH-http推送mot数据异常，motSourceEnum = {}，pushContent = {}，identification = {}，异常信息：{}",motSourceEnum,pushContent,identification,e);

        }
        return pushResp;
    }

}
