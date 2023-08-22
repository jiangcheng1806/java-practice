package com.jiangcz.application.jdk.annotation.simpleorm;

public class TableGenerateTest {
    public static void main(String[] args) {
        String sql = TableGenerator.generateSql(User.class);
        System.out.println(sql);
    }
}
