package cn.catguild.auth.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiyan
 * @date 2022/10/14 15:43
 */
@MapperScan("cn.catguild.auth.**.mapper")
@Configuration
public class MybatisPlusConfig {

	/**
	 * 新的分页插件、新多租户插件配置
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
		return interceptor;
	}

	//@Bean
	//public GlobalConfig globalConfig() {
	//	GlobalConfig conf = new GlobalConfig();
	//	GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
	//	dbConfig.setLogicDeleteField("deTime");
	//	dbConfig.setLogicDeleteValue("now()");
	//	dbConfig.setLogicNotDeleteValue("\"null\"");
	//	conf.setDbConfig(dbConfig);
	//	return conf;
	//}

}
