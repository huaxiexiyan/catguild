package cn.catguild.operation.presentation.config;

import cn.catguild.common.utility.transplant.DateTimeUtil;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author xiyan
 * @date 2023/10/19 11:23
 */
@Configuration
public class JacksonConfig {

    /**
     * Jackson全局转化long类型为String，解决jackson序列化时传入前端Long类型缺失精度问题
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);

            jacksonObjectMapperBuilder.serializers(new LocalDateTimeSerializer(DateTimeUtil.DATETIME_FORMAT));
            jacksonObjectMapperBuilder.serializers(new LocalDateSerializer(DateTimeUtil.DATE_FORMAT));
            jacksonObjectMapperBuilder.serializers(new LocalTimeSerializer(DateTimeUtil.TIME_FORMAT));

            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeUtil.DATETIME_FORMAT));
            jacksonObjectMapperBuilder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeUtil.DATE_FORMAT));
            jacksonObjectMapperBuilder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeUtil.TIME_FORMAT));
        };
    }

}
