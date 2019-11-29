package com.jiangcz.application.jdk.introspector;

import lombok.Data;

/**
 * Hello world!
 */
@Data
public class App {

    private String roofsName;

    private String roofsCode;

    private Workers workers;


    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    public App(String roofsName, String roofsCode) {
        super();
        this.roofsName = roofsName;
        this.roofsCode = roofsCode;
    }
}
