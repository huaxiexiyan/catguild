package cn.catguild.auth.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xiyan
 * @date 2023/8/7 14:22
 */
@Slf4j
public class DemoTest {

	@Test
	public void demo01(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encode = bCryptPasswordEncoder.encode("huaxiexiyan");
		log.info(encode);
	}

}
