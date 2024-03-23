package cn.catguild.ztest.mp;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import org.junit.jupiter.api.Test;

/**
 * @author xiyan
 * @date 2023/10/16 11:11
 */
public class PostgreSQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:postgresql://43.143.33.45:15432/catguild_test",
            "postgres", "kLYSE*XbFAu3RN6l")
            .build();

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig());
        generator.global(globalConfig().build());
        generator.packageInfo(packageConfig().build());
        generator.execute();
    }

}
