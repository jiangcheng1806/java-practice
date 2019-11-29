package com.jiangcz.application.common.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * jackson 工具类
 *
 */
@Slf4j
public class JacksonUtils {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator jg,
                                          SerializerProvider sp) throws IOException,
                            JsonProcessingException {
                        jg.writeString("");
                    }
                });
    }

    /**
     * 实体对象转换成Json字符串
     * @param object 实体对象
     * @return T
     */
    public  static String toJSONString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            log.error("toJSONString(Object object)", e);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json字符串转换成实体对象
     */
    public  static <T> T parseObject(String json, Class<T> clazz){
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonGenerationException e) {
            log.error("parseObject(String, Class<T> clazz)", e);
        } catch (IOException e) {
            log.error("parseObject(String, Class<T> clazz) IOException", e);
        }
        return null;
    }

    /**
     * 可以把Json字符串转换成List
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference){
        try {
            return (T) mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            log.error("parseObject(String, TypeReference<T>) JsonParseException", e);
        } catch (JsonMappingException e) {
            log.error("parseObject(String, TypeReference<T>) JsonMappingException", e);
        } catch (IOException e) {
            log.error("parseObject(String, TypeReference<T>)", e);
        }
        return null;
    }


    public static JsonNode readRootNode(String json) {
        return readAnyNode(json, null);
    }

    /**
     * 根据json key 获取任意层级的jsonNode
     */
    public static JsonNode readAnyNode(String json, String fieldName) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            if (StringUtils.isBlank(fieldName)) {
                return rootNode;
            }
            return rootNode.findValue(fieldName);
        } catch (IOException e) {
            log.error("readNode(String json,String fieldName)", e);
        }
        return null;
    }


    //创建jsonNode
    public static ObjectNode createObjectNode(){
        return mapper.createObjectNode();
    }

    /**
     *  格式化输出json
     */
    public  static <T> String console(T t){
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        } catch (JsonGenerationException e) {
            log.error("console(T t) JsonGenerationException", e);
        } catch (JsonMappingException e) {
            log.error("console(T t) JsonMappingException", e);
        } catch (IOException e) {
            log.error("console(T t)", e);
        }
        return json;
    }

    public static void main(String[] args) {
//        String json = "{\n" +
//                "    \"data\": {\n" +
//                "        \"billEntrustGoods\": [\n" +
//                "            {\n" +
//                "                \"count\": 100,\n" +
//                "                \"goodsName\": \"货品名称\",\n" +
//                "                \"goodsSpec\": \"规格\",\n" +
//                "                \"volumn\": 10.1,\n" +
//                "                \"weight\": 10.01\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"billTransport\": [\n" +
//                "            {\n" +
//                "                \"phoneCode\": \"司机电话\",\n" +
//                "                \"status\": \"运输状态\",\n" +
//                "                \"transportNo\": \"运输单编号\",\n" +
//                "                \"userName\": \"司机\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"consigId\": 10000,\n" +
//                "        \"consigNo\": \"货主自定义单号\",\n" +
//                "        \"expectArriveTime\": \"2017-11-24 11:52:20\",\n" +
//                "        \"expectPickTime\": \"2017-11-24 11:52:20\",\n" +
//                "        \"fleetId\": 10000,\n" +
//                "        \"recvAddress\": \"收货地址\",\n" +
//                "        \"recvCompany\": \"收货单位\",\n" +
//                "        \"recvName\": \"收货方联系人\",\n" +
//                "        \"recvPhone\": \"收货方电话\",\n" +
//                "        \"remark\": \"描述\",\n" +
//                "        \"sendAddress\": \"发货地址\",\n" +
//                "        \"sendCompany\": \"发货单位\",\n" +
//                "        \"sendName\": \"发货方联系人\",\n" +
//                "        \"sendPhone\": \"发货⽅方电话\",\n" +
//                "        \"transPrice\": 100\n" +
//                "    },\n" +
//                "    \"msg\": \"\",\n" +
//                "    \"status\": 20000\n" +
//                "}";
//        System.out.println(JacksonUtils.readAnyNode(json,"data").toString());
//        System.out.println(JacksonUtils.readAnyNode(json,"billTransport").toString());
//        System.out.println(JacksonUtils.readAnyNode(json,"transPrice").toString());

        ObjectNode send = JacksonUtils.createObjectNode();
        send.put("bookName", "Java");
        send.put("price", "100");
        System.out.println(JacksonUtils.console(send));

    }

}
