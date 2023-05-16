package com.jiangc.practice.jdk.io;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class HeaderUtil {
    public static void main(String[] args) {

        String peoplePath = "excelheader/人力花名册表头.txt";

        Map<String,Map<String,String>> headerMap = new HashMap<>();
        headerMap.put("people",new HeaderUtil().readHeaderMap("excelheader/人力花名册表头.txt"));
        headerMap.put("position",new HeaderUtil().readHeaderMap("excelheader/客户持仓明细表头.txt"));
        headerMap.put("org",new HeaderUtil().readHeaderMap("excelheader/客户持仓明细表头.txt"));

        System.out.println(JSON.toJSONString(headerMap));

    }

    public Map<String,String> readHeaderMap(String path){

        List<String> lines = new HeaderUtil().readLine(path);
        Map<String,String> headerMap = new LinkedHashMap<>();
        lines.forEach(s -> {
            if (StringUtils.isNotBlank(s)){
                String[] arr1 = s.split("\t");
                List<String> arr = new ArrayList<>();
                for (int i = 0; i < arr1.length; i++) {
                    String[] arr2 = arr1[i].split(" ");
                    for (int i1 = 0; i1 < arr2.length; i1++) {
                        arr.add(arr2[i1]);
                    }
                }
                List<String> strings = arr.stream().filter(s1 -> !s1.equals("")).collect(Collectors.toList());
                headerMap.put(strings.get(0).replace("`","").trim(),strings.get(2).replace("'","").trim());
            }
        });
        headerMap.keySet().forEach(s -> {
            System.out.println(s+"==>"+headerMap.get(s));
        });
        return headerMap;
    }

    public List<String> readLine(String path){
        List<String> lineArr = new ArrayList<>();
        try {
            InputStream is = new ClassPathResource(path).getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String strTmp = "";
            while((strTmp = bufferedReader.readLine())!=null){
//                System.out.println(strTmp);
                lineArr.add(strTmp);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineArr;
    }


}
