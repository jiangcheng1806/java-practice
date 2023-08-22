package com.jiangcz.application.elk.controller;

import com.alibaba.fastjson.JSON;
import com.jiangcz.application.elk.annotation.SysLog;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @SysLog(isLog = true)
    @RequestMapping(value = "/getDataList", method = RequestMethod.POST)
    public ApiResult getInputDataList(@RequestBody VersionVo vo) {
        ApiResult result = new ApiResult();
        result.setStatus(0);
        result.setCode("200000");
        result.setMsg("success");
        return result;
    }

    public static void main(String[] args) {
        VersionVo vo = new VersionVo();
        TestController controller = new TestController();
        ApiResult result = controller.getInputDataList(vo);
        System.out.println(JSON.toJSON(result));
    }
}
