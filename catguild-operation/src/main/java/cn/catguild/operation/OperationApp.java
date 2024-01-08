package cn.catguild.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients({"cn.catguild.auth.api.client"})
@SpringBootApplication
public class OperationApp {
    public static void main(String[] args) {
        SpringApplication.run(OperationApp.class, args);
    }

}