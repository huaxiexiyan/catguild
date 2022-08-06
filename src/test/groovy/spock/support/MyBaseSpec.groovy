package spock.support

import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.jdbc.datasource.init.ScriptUtils
import spock.lang.Specification

/**
 * dao 基础测试
 */
@Slf4j
class MyBaseSpec extends Specification {

    /**
     * 获取当前数据源
     */
    def dataSource = DataSourceHolder.getDataSource()

    /**
     * 执行 sql 方法
     *
     * @param resourcePath
     */
    def "executeSqlScriptFile"(String sqlPath) {
        // 获取连接
        def connection = dataSource.getConnection()
        try {
            // 执行 sql 脚本
            Resource resource = new FileSystemResource(SpockUtil.getResourceFile(sqlPath))
            ScriptUtils.executeSqlScript(connection, resource)
            log.info("============>>>> 初始化表结构执行")
        } finally {
            if (connection && !connection.isClosed()) {
                connection.close()
            }
        }
    }

    /**
     * 删除表
     *
     * @param table
     */
    def "dropTables"(String table) {
        // 获取数据库连接
        def session = MapperUtil.getSqlSession()
        try {
            // 执行 sql
            String dropSql = """DROP TABLE IF EXISTS `${table}` """;
            def execute = new Sql(dataSource).execute(dropSql)
            log.info("============>>>> 删除 {} 表结构执行", table)
        } finally {
            if (session) {
                // 在测试运行中， session只有一个，不能关闭，但需要提交，不然会影响到下一个单测
                session.commit()
            }
        }
    }


}
