package spock.support;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * 操作 mapper 工具
 * 避免通过Spring启动加载上下文信息
 *
 * @author zhi.liu
 * @date 2021-11-04 11:37
 */
public class MapperUtil {

	private static SqlSession sqlSession;

	/**
	 * 通过MyBatis的SqlSession启动mapper实例
	 *
	 * @param mapperClass
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getMapper(Class<T> mapperClass) {
		return (T) getSqlSession().getMapper(mapperClass);
	}

	/**
	 * 获取 SqlSession
	 *
	 * @return
	 */
	public static SqlSession getSqlSession() {
		if (sqlSession == null) {
			// 1、设置环境
			Configuration configuration = new Configuration();
			final Environment environment = new Environment.Builder("unit")
				.dataSource(DataSourceHolder.getDataSource())
				.transactionFactory(new JdbcTransactionFactory())
				.build();
			configuration.setEnvironment(environment);

			// 2、注册别名
			configuration.getTypeAliasRegistry().registerAliases("cn.catguild.guild.infrastructure.persistence.entity");
			configuration.addMappers("cn.catguild.guild.infrastructure.persistence.mapper");

			final SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);

			// 3、打开一个新会话(要设置为自动提交)
			sqlSession = factory.openSession(true);
		}

		return sqlSession;
	}


}
