package cn.catguild.user.test

import groovy.util.logging.Slf4j
import org.dbunit.database.IDatabaseConnection
import org.dbunit.ext.h2.H2Connection

@Slf4j
class MyIDatabaseConnection {


    private final static MyIDatabaseConnection instance = new MyIDatabaseConnection();

    private MyIDatabaseConnection() {
    }

    /**
     * 获取连接实例(单例)
     */
    static MyIDatabaseConnection getInstance() {
        return instance;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    IDatabaseConnection getConnection() {
        try {
            return new H2Connection(DataSourceHolder.getDataSource().getConnection(), "");
        } catch (Exception e) {
            log.error("============>>> 数据库连接失败", e);
        }
        return null;
    }

}
