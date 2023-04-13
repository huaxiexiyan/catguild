package cn.catguild.user.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源生成
 *
 * @author zhi.liu
 * @date 2021-11-04 19:52
 */
public class DataSourceHolder {

	private static DataSource dataSource;

	public static DataSource getDataSource() {
		if (dataSource == null || ((HikariDataSource) dataSource).getJdbcUrl() == null) {
			HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setDriverClassName("org.h2.Driver");

			// DB_CLOSE_DELAY=-1; 默认情况下，关闭与数据库的最后一个连接将关闭该数据库。对于内存数据库，这意味着内容丢失。
			hikariConfig.setJdbcUrl("jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_UPPER=FALSE;DB_CLOSE_DELAY=-1;LOCK_MODE=3");
			hikariConfig.setIdleTimeout(10000);
			hikariConfig.setMaximumPoolSize(20);
			hikariConfig.setConnectionTestQuery("SELECT 1");
			hikariConfig.setConnectionTimeout(30000);
			hikariConfig.setMaxLifetime(2000000);
			hikariConfig.setMinimumIdle(3);
			hikariConfig.setPoolName("SpringBootHikariCP");
			hikariConfig.setAutoCommit(true);

			final Properties properties = new Properties();
			properties.setProperty("cachePrepStmts", "true");
			properties.setProperty("prepStmtCacheSize", "250");
			properties.setProperty("prepStmtCacheSqlLimit", "2048");
			hikariConfig.setDataSourceProperties(properties);
			dataSource = new HikariDataSource(hikariConfig);
		}
		return dataSource;
	}

	public static DataSource getDataSource2() {
		try {
			if (dataSource == null || ((SimpleDriverDataSource) dataSource).getUrl() == null) {
				Class.forName("org.h2.Driver");
				// DB_CLOSE_DELAY=-1; 默认情况下，关闭与数据库的最后一个连接将关闭该数据库。对于内存数据库，这意味着内容丢失。
				dataSource = new SimpleDriverDataSource(new org.h2.Driver(),
					"jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_UPPER=FALSE;DB_CLOSE_DELAY=-1;LOCK_MODE=1;AUTOCOMMIT=ON;TRACE_LEVEL_SYSTEM_OUT=3");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	public static DataSource getDataSource3() {
		try {
			if (dataSource == null) {
				dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_UPPER=FALSE;DB_CLOSE_DELAY=-1;LOCK_MODE=1;AUTOCOMMIT=ON;TRACE_LEVEL_SYSTEM_OUT=2",
					"", "");
				//Class.forName("org.h2.Driver");
				//// DB_CLOSE_DELAY=-1; 默认情况下，关闭与数据库的最后一个连接将关闭该数据库。对于内存数据库，这意味着内容丢失。
				//dataSource = new SimpleDriverDataSource(new org.h2.Driver(),
				//        "jdbc:h2:mem:test;MODE=MYSQL;DATABASE_TO_UPPER=FALSE;DB_CLOSE_DELAY=-1;LOCK_MODE=1;AUTOCOMMIT=ON;TRACE_LEVEL_SYSTEM_OUT=3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}
