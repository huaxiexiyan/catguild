package spock.support

import groovy.util.logging.Slf4j
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.csv.CsvDataSet
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder

@Slf4j
class MyDbUnitUtil {

    /**
     * 加载 csv 数据源
     *
     * @param filePath 文件路径（相当于测试类）
     */
    static IDataSet loadCsv(String filePath) {
        if (SpockUtil.isBlank(filePath)) {
            return null
        }
        new CsvDataSet(SpockUtil.getResourceFile(filePath));
    }


    /**
     * 加载 xml 数据源
     *
     * @param filePath 文件路径（相当于测试类）
     */
    static IDataSet loadXml(String filePath) {
        if (SpockUtil.isBlank(filePath)) {
            return null
        }
        new FlatXmlDataSetBuilder().build(SpockUtil.getResourceFile(filePath))
    }

    /**
     * 加载 xml 格式 String
     *
     * @param xmlStr
     */
    static IDataSet loadXmlStr(String xmlStr) {
        if (SpockUtil.isBlank(xmlStr)) {
            return null
        }
        new FlatXmlDataSetBuilder().build(new StringReader(xmlStr))
    }

}
