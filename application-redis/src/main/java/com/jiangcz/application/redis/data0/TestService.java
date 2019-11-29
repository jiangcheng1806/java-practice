package com.jiangcz.application.redis.data0;

import com.jiangcz.application.common.util.HttpClientUtil;
import com.jiangcz.application.common.util.JacksonUtils;
import com.jiangcz.application.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import java.util.*;

@Slf4j
public class TestService {

    private String testAccessKey = "ACK-ZHNY-V46U2N";
    private String testDomain = "https://www.dingding56.com/api/getexam.aspx";

    /**
     * 查询考试结果
     */
    public List<TestRecordVo> selectTestRecordVoList(String truckerPhone, String truckerIdNumber, String isPassed){
        List<TestRecordVo> list = new ArrayList<>();
        Map<String, String> headerParamMap = new HashMap<>(16);
        Map<String, String> requestParamMap = new HashMap<>(16);
        requestParamMap.put("accessKey", testAccessKey);
        requestParamMap.put("isPassed", isPassed);
        if (!StringUtils.isEmpty(truckerIdNumber)) {
            requestParamMap.put("searchText", truckerIdNumber);
        } else {
            requestParamMap.put("searchText", truckerPhone);
        }
        log.info("预约查询库区接口参数：【{}】,header参数：【{}】查找对应库区的出库单",requestParamMap,null);
        String param = JacksonUtils.toJSONString(requestParamMap);
        HttpClientUtil httpClientService = new HttpClientUtil();
        TestStorageResponse response = httpClientService.sendPostRequest(testDomain, requestParamMap, null,TestStorageResponse.class,null);
        log.info("预约查询库区接口参数：【{}】查找对应库区的出库单:【{}】",requestParamMap, JacksonUtils.toJSONString(response));
        if (response != null && response.getStatus() != null &&  "0000".equals(response.getStatus())) {
            JSONObject jsonString2 = JSONObject.fromObject(response);
            String data = jsonString2.getJSONArray("data").toString();
            if (!StringUtils.isEmpty(data)) {
                List<TestRecordVo> records = com.alibaba.fastjson.JSONObject.parseArray(data, TestRecordVo.class);
                list.addAll(records);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        TestService testService = new TestService();

        List<TestRecordVo> testRecordVoList = testService.selectTestRecordVoList(null,null,null);

        Collections.sort(testRecordVoList, Comparator.comparing(TestRecordVo::getId));

        System.out.println("--------------");
        log.info(JacksonUtils.toJSONString(testRecordVoList));


    }
}
