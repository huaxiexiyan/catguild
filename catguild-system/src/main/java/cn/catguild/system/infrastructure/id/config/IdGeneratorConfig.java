package cn.catguild.system.infrastructure.id.config;

import cn.catguild.system.infrastructure.id.strategy.IdGenerator;
import cn.catguild.system.infrastructure.id.strategy.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiyan
 * @date 2023/8/1 15:51
 */
@Configuration
public class IdGeneratorConfig {

    @Bean
    public IdGenerator snowflake(){
        return new SnowflakeIdGenerator(1,1);
    }

}
