package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jiangc"})
public class ExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class);
    }
}
