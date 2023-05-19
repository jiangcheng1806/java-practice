package com.jiangc.practice.jdk.io;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DubboReader {
    public static void main(String[] args) {

//        String file01 = "data-supply-statisc-provider-registry-chtfund.properties.txt";
//        String file02 = "data-supply-web-consumer.cache.txt";
//        String file03 = "data-supply-native-datacenter-registry.properties";
//        String file04 = "data-supply-native-provider-registry.properties";
//        String file05 = "data-supply-statisc-provider-registry.properties";
//        String file06 = "web_work.properites";
        String file06 = "data-supply-web-consumer.cache.txt";


        InputStream is = DubboReader.class.getClassLoader().getResourceAsStream(file06);
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        String temp = "";
        List<String> ls = new ArrayList<>();
        try {
            while ((temp = br.readLine()) != null){
                ls.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> lsIp = new ArrayList<>();

        List<Pair<String,String>> pairs = new ArrayList<>();

        ls.forEach(s -> {
            List<String> lsIpTemp = lsIp(s);
            lsIp.addAll(lsIpTemp);

            List<Pair<String,String>> pairs1 = extraIpService(s);
            pairs.addAll(pairs1);
        });

        System.out.println("1=>"+ JSON.toJSONString(lsIp));

        List<String> distinctIp = lsIp.stream().distinct().collect(Collectors.toList());
        System.out.println("2=>"+ JSON.toJSONString(distinctIp));
//        2=>["10.100.12.202","10.100.40.165","10.100.40.164","10.100.40.28","10.100.40.30","10.100.40.31","10.100.40.29","10.100.40.152","10.100.40.163","10.100.40.167","10.100.40.166"]


        List<String> net0 = Arrays.asList("10.100.0.0","10.100.31.255");
        List<String> net1 = Arrays.asList("10.100.32.0","10.100.95.255");

        //List<Long> netLo0 = net0.stream().map(s -> Long.valueOf(s.replace(".",""))).collect(Collectors.toList());
        List<Long> netLo0 = net0.stream().map(DubboReader::toLongVal).collect(Collectors.toList());
        //List<Long> netLo1 = net1.stream().map(s -> Long.valueOf(s.replace(".",""))).collect(Collectors.toList());
        List<Long> netLo1 = net1.stream().map(DubboReader::toLongVal).collect(Collectors.toList());

        List<String> gfIp = new ArrayList<>();
        List<String> mzIp = new ArrayList<>();
        distinctIp.forEach(s -> {
//            String s1 = s.replace(".", "");
//            Long l0 = Long.valueOf(s1);
            Long l0 = toLongVal(s);
            if (l0 >= netLo0.get(0) && l0 <= netLo0.get(1)){
                gfIp.add(s);
            }
            if (l0 >= netLo1.get(0) && l0 <= netLo1.get(1)){
                mzIp.add(s);
            }
        });

        System.out.println("gfIp=>"+JSON.toJSONString(gfIp));
        System.out.println("mzIp=>"+JSON.toJSONString(mzIp));

        List<Pair<String, String>> pairs1 = pairs.stream().filter(pair -> {
            String left = pair.getLeft();
            String ip = left.replace("\\", "").split(":")[0];
            return mzIp.contains(ip);
        }).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(pairs1));

        System.out.println("=============================================\n");

        List<Pair<String, String>> pairs2 = pairs1.stream().distinct().collect(Collectors.toList());


        List<String> lsApis = new ArrayList<>();
        pairs2.forEach(pair0 ->{
            String s1 = pair0.getLeft().replace("\\","");
            String s2 = pair0.getRight();

            System.out.println(s1+","+s2);
            lsApis.add(s2);
        });

        List<String> lsApis1 = lsApis.stream().distinct().collect(Collectors.toList());

        System.out.println("=============================================\n");

        System.out.println("lapis=>"+JSON.toJSONString(lsApis1));
        lsApis1.forEach(System.out::println);

    }

    public static List<String> lsIp(String text){
        Pattern pattern1 = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher matcher1 = pattern1.matcher(text);
//        boolean b = matcher1.find();
//        System.out.println(b);
        List<String> lsIp = new ArrayList<>();
        while (matcher1.find()){
//            System.out.println("Match: " + matcher1.group());
            String ip = matcher1.group();
            lsIp.add(ip);
        }
        return lsIp;
    }

    public static Long toLongVal(String ip){

        String[] split = ip.split("\\.");
        StringBuffer sb = new StringBuffer();
        for (String s : split) {
            if (s.length() < 3){
                sb.append(s);
                for (int i = 0; i < (3-s.length()); i++) {
                    sb.append("0");
                }
            }else {
                sb.append(s);
            }
        }

        String newIp = sb.toString();
        return Long.valueOf(newIp);
    }

    /**
     * [{"10.100.40.165\:30999":"com.chtwm.datacenter.natives.service.customer.CustomerStatusService"},{"10.100.40.164\:30999":"com.chtwm.datacenter.natives.service.customer.CustomerStatusService"}]
     * @param content
     * @return
     */
    static List<Pair<String,String>> extraIpService(String content){
        String regex = "dubbo\\\\://((\\d+\\.){3}\\d+\\\\:\\d+)/(([a-z]+.)+.[a-zA-Z]+)\\?";
        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher2 = pattern2.matcher("jiangcheng1806@163.com");
        Matcher matcher3 = pattern.matcher(content);
        List<Pair<String,String>> ls = new ArrayList<>();
        while (matcher3.find()){
            System.out.println("提取数据匹配=>"+matcher3.group());
            Pair<String,String> pair = Pair.of(matcher3.group(1),matcher3.group(3));
            ls.add(pair);
        }
        return ls;
    }

}
