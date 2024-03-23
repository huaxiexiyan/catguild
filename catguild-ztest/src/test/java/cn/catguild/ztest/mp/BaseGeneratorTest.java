package cn.catguild.ztest.mp;

import com.baomidou.mybatisplus.generator.config.*;

/**
 * @author xiyan
 * @date 2023/10/16 11:10
 */
public class BaseGeneratorTest {

    ///**
    // * 执行数据库脚本
    // */
    //protected static void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException {
    //    Connection conn = dataSourceConfig.getConn();
    //    InputStream inputStream = H2CodeGeneratorTest.class.getResourceAsStream("/sql/init.sql");
    //    ScriptRunner scriptRunner = new ScriptRunner(conn);
    //    scriptRunner.setAutoCommit(true);
    //    scriptRunner.runScript(new InputStreamReader(inputStream));
    //    conn.close();
    //}

    /**
     * 策略配置
     */
    protected static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder()
                //.likeTable(new LikeTable("business"))
                .addInclude("auth_user")
                .entityBuilder()
                .enableLombok()
                .build()
                .mapperBuilder()
                .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                .enableBaseColumnList()//启用xml文件中的BaseColumnList
                .build();
    }

    /**
     * 全局配置
     */
    protected static GlobalConfig.Builder globalConfig() {
        //disableOpenDir	禁止打开输出目录	默认值:true
        //outputDir(String)	指定输出目录	/opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
        //author(String)	作者名	baomidou 默认值:作者
        //enableKotlin	开启 kotlin 模式	默认值:false
        //enableSwagger	开启 swagger 模式	默认值:false
        //dateType(DateType)	时间策略	DateType.ONLY_DATE 默认值: DateType.TIME_PACK
        //commentDate(String)	注释日期	默认值: yyyy-MM-dd
        return new GlobalConfig.Builder()
                .disableOpenDir()
                .author("xiyan")
                .outputDir("D://Document//Programme//my-project//catguild//catguild-ztest//src//test//java//mp");
    }

    /**
     * 包配置
     */
    protected static PackageConfig.Builder packageConfig() {
        //parent(String)	父包名	默认值:com.baomidou
        //moduleName(String)	父包模块名	默认值:无
        //entity(String)	Entity 包名	默认值:entity
        //service(String)	Service 包名	默认值:service
        //serviceImpl(String)	Service Impl 包名	默认值:service.impl
        //mapper(String)	Mapper 包名	默认值:mapper
        //xml(String)	Mapper XML 包名	默认值:mapper.xml
        //controller(String)	Controller 包名	默认值:controller
        //other(String)	自定义文件包名	输出自定义文件时所用到的包名
        //pathInfo(Map<OutputFile, String>)	路径配置信息	Collections.singletonMap(OutputFile.mapperXml, "D://")
        return new PackageConfig.Builder()
                .parent("cn.catguild.auth");
    }

    /**
     * 模板配置
     */
    protected static TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder();
    }

    /**
     * 注入配置
     */
    protected static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }


}
