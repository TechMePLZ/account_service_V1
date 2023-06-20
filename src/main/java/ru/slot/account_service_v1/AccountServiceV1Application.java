package ru.slot.account_service_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceV1Application {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceV1Application.class, args);
    }

}
