package com.qiao.gaokaoforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class GaokaoforecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaokaoforecastApplication.class, args);
    }

}
