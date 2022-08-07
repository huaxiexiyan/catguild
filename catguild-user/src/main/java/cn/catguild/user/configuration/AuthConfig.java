package cn.catguild.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xiyan
 * @date 2022-08-07 11:11
 */
@Configuration
public class AuthConfig {

	public static final  byte[] TOKEN_SECRET = "VJo8aQAzgxXTwCRr3muLuyDQwsQuXhx3lBWfmBH1aQ72.z/MmWN6".getBytes();

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
