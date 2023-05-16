package com.jiangc.practice.jdk.io;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileUtil {
    public static void main(String[] args) {

        String dir = FileUtil.class.getResource("/").getPath();

        String file01 = "data-supply-statisc-provider-registry-chtfund.properties.txt";
        String file02 = "data-supply-web-consumer.cache.txt";
        System.out.println(dir);

        try {
            File file = ResourceUtils.getFile(dir + file01);
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8.toString());
            System.out.println(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();
        try {
            InputStream is = new ClassPathResource(file01).getInputStream();
//        Properties properties = new Properties();
//            properties.load(is);
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            try {
                while ((line = br.readLine()) != null){
                    list.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        InputStream is01 = FileUtil.class.getClassLoader().getResourceAsStream(file01);
        Reader reader = new InputStreamReader(is01);
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        try {
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(JSON.toJSONString(list));
    }
}
