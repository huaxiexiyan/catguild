package cn.catguild.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {
        "nacos.namespace=9b4d99af-a600-4eb5-b096-cd1d6cd9159b",
        "nacos.password=iica72aSX45soWTL",
        "nacos.server.address=43.143.33.45:18848",
        "nacos.username=catguild-test"
})
public class SystemAppTest {

    @Test
    public void start(){
        log.info("启动");
    }

}