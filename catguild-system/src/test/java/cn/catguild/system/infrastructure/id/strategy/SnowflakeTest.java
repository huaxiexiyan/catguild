package cn.catguild.system.infrastructure.id.strategy;

import org.junit.jupiter.api.Test;

public class SnowflakeTest {

    @Test
    void nextId() {
        Snowflake snowflake = new Snowflake(1, 1);
        System.out.println(snowflake.nextId());
    }

}