package com.jiangc.practice.jdk.io;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("^([0-9]{3})([a-z]{3}-[0-9]{5})");
        Matcher matcher = pattern.matcher("788asd-12345");
        boolean bl = matcher.find();
        System.out.println(bl);

        String group = matcher.group(0);
        System.out.println(group);
        String group1 = matcher.group();
        System.out.println(group1);
        String group2 = matcher.group(1);
        System.out.println(group2);
        String group3 = matcher.group(2);
        System.out.println(group3);


//        Pattern pattern1 = Pattern.compile("^.*://(\\d{2,3}.\\d{3,3}.\\d{2,3}.\\d{2,3}\\:\\d{2,6}/\\s+\\?).*");
//        Pattern pattern1 = Pattern.compile("//[0-9]+.[0-9]+.[0-9]+.[0-9]+\\:[0-9]+/com*?.*");
        String text = "com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService\\:1.0.0=empty\\://10.100.12.202/com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService?application\\=data-supply-statisc-provider&category\\=configurators&check\\=false&default.check\\=false&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService&logger\\=slf4j&methods\\=countIssuanceByCurMonthAndToDay,countReserveByCurMonthAndToDay,countAccountByCurMonthAndToDay&pid\\=89317&retries\\=0&revision\\=1.0.0&side\\=consumer&timeout\\=400000&timestamp\\=1642766667742&version\\=1.0.0 empty\\://10.100.12.202/com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService?application\\=data-supply-statisc-provider&category\\=routers&check\\=false&default.check\\=false&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService&logger\\=slf4j&methods\\=countIssuanceByCurMonthAndToDay,countReserveByCurMonthAndToDay,countAccountByCurMonthAndToDay&pid\\=89317&retries\\=0&revision\\=1.0.0&side\\=consumer&timeout\\=400000&timestamp\\=1642766667742&version\\=1.0.0 dubbo\\://10.100.40.165\\:30999/com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService?anyhost\\=true&application\\=data-supply-native-datacenter-provider&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService&logger\\=slf4j&methods\\=countIssuanceByCurMonthAndToDay,countReserveByCurMonthAndToDay,countAccountByCurMonthAndToDay&pid\\=41217&revision\\=1.0.0&side\\=provider&timestamp\\=1642765305586&version\\=1.0.0 dubbo\\://10.100.40.164\\:30999/com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService?anyhost\\=true&application\\=data-supply-native-datacenter-provider&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.performance.PerformanceStatisticsService&logger\\=slf4j&methods\\=countIssuanceByCurMonthAndToDay,countReserveByCurMonthAndToDay,countAccountByCurMonthAndToDay&pid\\=20175&revision\\=1.0.0&side\\=provider&timestamp\\=1642765217292&version\\=1.0.0";
        Pattern pattern1 = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher matcher1 = pattern1.matcher(text);
        boolean b = matcher1.find();
        System.out.println(b);
        while (matcher1.find()){
            System.out.println("Match: " + matcher1.group());
        }



        String regex = "(?<=(^|[^a-zA-Z0-9]))([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,})(?=([^a-zA-Z0-9]|$))";

        /**
         *
         * (?<=(^|[^a-zA-Z0-9]))
         * 这个断言匹配以字符串开头或者非字母数字字符为前缀的子字符串。它使用饥饿算法，即以尽可能多的字符匹配前缀。这个断言的作用是判断邮件地址前面没有其他字母数字字符。
         *
         * (?=([^a-zA-Z0-9]|$))
         * 这个断言匹配以字符串结尾或者非字母数字字符为后缀的子字符串。它同样使用饥饿算法，即以尽可能多的字符匹配后缀。这个断言的作用是判断邮件地址后面没有其他字母数字字符。
         *
         * 这个正则表达式可以正确匹配常见的电子邮件地址，而且利用了饥饿算法可以提高匹配效率。
         *
         * 网上有推荐 \w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}
         *
         */

        Pattern pattern2 = Pattern.compile(regex);
//        Matcher matcher2 = pattern2.matcher("jiangcheng1806@163.com");
        Matcher matcher2 = pattern2.matcher("jiangcheng1806@gmail.com");
        while (matcher2.find()){
            System.out.println("饥饿算法匹配=>"+matcher2.group());
        }


        /**
         *
         * dubbo\://10.100.40.165\:30999/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?
         *
         *
         *
         */
        String text1 = "com.chtwm.datacenter.natives.service.customer.CustomerStatusService\\:1.0.0=empty\\://10.100.12.202/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?application\\=data-supply-statisc-provider&category\\=configurators&check\\=false&default.check\\=false&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.customer.CustomerStatusService&logger\\=slf4j&methods\\=selectCustomerStatus,selectCustomerTransCategory&pid\\=89317&retries\\=0&revision\\=1.0.0&side\\=consumer&timeout\\=400000&timestamp\\=1642766667930&version\\=1.0.0 empty\\://10.100.12.202/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?application\\=data-supply-statisc-provider&category\\=routers&check\\=false&default.check\\=false&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.customer.CustomerStatusService&logger\\=slf4j&methods\\=selectCustomerStatus,selectCustomerTransCategory&pid\\=89317&retries\\=0&revision\\=1.0.0&side\\=consumer&timeout\\=400000&timestamp\\=1642766667930&version\\=1.0.0 dubbo\\://10.100.40.165\\:30999/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?anyhost\\=true&application\\=data-supply-native-datacenter-provider&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.customer.CustomerStatusService&logger\\=slf4j&methods\\=selectCustomerStatus,selectCustomerTransCategory&pid\\=41217&revision\\=1.0.0&side\\=provider&timestamp\\=1642765305389&version\\=1.0.0 dubbo\\://10.100.40.164\\:30999/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?anyhost\\=true&application\\=data-supply-native-datacenter-provider&dubbo\\=2.5.3&interface\\=com.chtwm.datacenter.natives.service.customer.CustomerStatusService&logger\\=slf4j&methods\\=selectCustomerStatus,selectCustomerTransCategory&pid\\=20175&revision\\=1.0.0&side\\=provider&timestamp\\=1642765217088&version\\=1.0.0";
//        String regex1 = "dubbo\\://10.100.40.165\\:30999/com.chtwm.datacenter.natives.service.customer.CustomerStatusService?";

        String regex1 = "dubbo\\\\://((\\d+\\.){3}\\d+\\\\:\\d+)/(([a-z]+.)+.[a-zA-Z]+)\\?";

        Pattern pattern3 = Pattern.compile(regex1);
//        Matcher matcher2 = pattern2.matcher("jiangcheng1806@163.com");
        Matcher matcher3 = pattern3.matcher(text1);
        List<Pair<String,String>> ls = new ArrayList<>();
        while (matcher3.find()){
            System.out.println("提取数据匹配=>"+matcher3.group());
            Pair<String,String> pair = Pair.of(matcher3.group(1),matcher3.group(3));
            ls.add(pair);
        }

        System.out.println(JSON.toJSONString(ls));

    }
}
