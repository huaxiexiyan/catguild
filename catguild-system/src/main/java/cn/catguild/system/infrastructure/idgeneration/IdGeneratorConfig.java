package cn.catguild.system.infrastructure.idgeneration;

import cn.catguild.system.infrastructure.idgeneration.strategy.IdGenerator;
import cn.catguild.system.infrastructure.idgeneration.strategy.Snowflake;
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
        return new Snowflake(1,1,true);
    }

}
