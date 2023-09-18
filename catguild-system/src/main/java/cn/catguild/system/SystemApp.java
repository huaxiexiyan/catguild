package cn.catguild.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SystemApp {

  public static void main(String[] args) {
    SpringApplication.run(SystemApp.class, args);
  }

}
