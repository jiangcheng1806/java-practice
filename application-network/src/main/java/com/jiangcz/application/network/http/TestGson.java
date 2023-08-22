package com.jiangcz.application.network.http;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestGson {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>begin<<<<<<<<<<<<<<<<<<<<<");
        TestVo testVo = new TestVo();
        testVo.setStatus("S");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        testVo.setCheckDate(s);
        testVo.setCheckResult("检查成功");


        Gson gson = new Gson();
        String json = gson.toJson(testVo);
        System.out.println(json);


    }
}
