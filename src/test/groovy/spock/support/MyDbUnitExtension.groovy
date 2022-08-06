package spock.support

import groovy.util.logging.Slf4j
import org.spockframework.runtime.extension.IAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.MethodInfo

@Slf4j
class MyDbUnitExtension implements IAnnotationDrivenExtension<MyDbUnit> {

    /**
     * 适用与 cleanup()、setup() 方法
     *
     * @param annotation the annotation found on the fixture method
     * @param fixtureMethod the annotated fixture method
     */
    @Override
    void visitFixtureAnnotation(MyDbUnit annotation, MethodInfo fixtureMethod) {
        fixtureMethod.addInterceptor(new MyDbUnitInterceptor(annotation));
    }

    /**
     * 每个测试方法上有这个注解都会调用一次
     *
     * @param annotation the annotation found on the feature method
     * @param feature the annotated feature method
     */
    @Override
    void visitFeatureAnnotation(MyDbUnit annotation, FeatureInfo feature) {
        feature.getFeatureMethod().addInterceptor(new MyDbUnitInterceptor(annotation));
    }

}
