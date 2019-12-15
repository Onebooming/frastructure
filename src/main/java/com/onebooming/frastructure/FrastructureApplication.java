package com.onebooming.frastructure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.onebooming.frastructure.dao")
@EnableCaching
public class FrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrastructureApplication.class, args);
    }

}
