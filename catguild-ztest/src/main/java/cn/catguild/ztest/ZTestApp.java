package cn.catguild.ztest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiyan
 * @date 2023/3/18 12:25
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ZTestApp {

	public static void main(String[] args) {
		SpringApplication.run(ZTestApp.class, args);
	}

}
