package com.jiangc.practice.jdk.string;

import org.openjdk.jol.info.ClassLayout;

public class CountEmptyObjectSize {
    public static void main(String[] args) {
        CountEmptyObjectSize obj = new CountEmptyObjectSize();

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


        Dog dog = new Dog();
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
        System.out.println(dog.hashCode());
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }
}

class Dog{}
