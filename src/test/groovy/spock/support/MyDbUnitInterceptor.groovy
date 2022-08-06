package spock.support

import groovy.util.logging.Slf4j
import groovy.xml.MarkupBuilder
import org.dbunit.dataset.CompositeDataSet
import org.dbunit.dataset.IDataSet
import org.dbunit.operation.DatabaseOperation
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

/**
 * MyDdUnit 注解的拦截器
 */
@Slf4j
class MyDbUnitInterceptor implements IMethodInterceptor {

    protected final MyDbUnit myDbUnit;

    MyDbUnitInterceptor(MyDbUnit myDbUnit) {
        //  在完全启动前调用
        this.myDbUnit = myDbUnit
    }

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        // 获取数据集
        def dataSet = findDataSet(invocation)

        log.info("=========>>> 拦截器构造方法执行, 执行数据集{}", dataSet)

        // 执行数据更新
        def connection = MyIDatabaseConnection.getInstance().getConnection()
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet)

        invocation.proceed()
    }

    CompositeDataSet findDataSet(IMethodInvocation invocation) {
        def dataSets = []
        // 加载简单数据源
        def content = myDbUnit.content()
        if (content && Closure.isAssignableFrom(content) && content != Closure.class) {
            def instance = content.newInstance(invocation.instance, invocation.instance)
            String dataSetAsString = writeXmlDataSet(instance)
            def str = MyDbUnitUtil.loadXmlStr(dataSetAsString)
            dataSets.add(str)
        }
        // 加载文件
        def xml = MyDbUnitUtil.loadXml(myDbUnit.xmlLocation())
        if (xml) {
            dataSets.add(xml)
        }
        def csv = MyDbUnitUtil.loadCsv(myDbUnit.csvLocation())
        if (csv) {
            dataSets.add(csv)
        }

        new CompositeDataSet(dataSets as IDataSet[], true)
    }


    /**
     * 将 闭包的 内转换成 xml 格式 String 类型
     *
     * @param dataSetClosure
     * @return
     */
    private static String writeXmlDataSet(Closure dataSetClosure) {
        def xmlWriter = new StringWriter()
        def builder = new MarkupBuilder(xmlWriter)
        builder.dataset(dataSetClosure)
        return xmlWriter as String
    }

}
