package cn.catguild.system.infrastructure.id.strategy;

import cn.catguild.system.infrastructure.idgeneration.strategy.Snowflake;
import org.junit.jupiter.api.Test;

public class SnowflakeTest {

    @Test
    void nextId() {
        Snowflake snowflake = new Snowflake(1, 1,true);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowflake.nextId());
        }
    }

}