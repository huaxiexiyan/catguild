package spock.support

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * 方便 数据注入的 注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtensionAnnotation(MyDbUnitExtension.class)
@interface MyDbUnit {

    /**
     * <pre>
     * content = {*    your_table_name(id: 1, name: 'xxx', age: 21)
     *    your_table_name(id: 2, name: 'xxx', age: 22)
     *})
     </pre>
     * @return
     */
    Class<? extends Closure> content() default Closure.class;

    /**
     * xml存放路径(相对于测试类)
     * @return
     */
    String xmlLocation() default "";

    /**
     * csv存放路径(相对于测试类)
     * @return
     */
    String csvLocation() default "";


}