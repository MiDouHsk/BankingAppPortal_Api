package com.bank_v2.bankingportal_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class BankingPortalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingPortalApiApplication.class, args);
    }

}
