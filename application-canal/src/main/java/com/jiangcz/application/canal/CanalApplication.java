package com.jiangcz.application.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 类名称：CanalApplication<br>
 * 类描述：<br>
 * 创建时间：2019年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@SpringBootApplication
@ComponentScan({"com.jiangcz.application"})
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }
}
