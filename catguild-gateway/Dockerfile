# 一阶段编译打包
FROM openjdk:17-jdk-slim AS build-env
ADD settings.gradle build.gradle gradlew   /app/
ADD gradle  /app/gradle/
RUN cd /app/ \
    && ls
RUN chmod +x /app/gradlew \
   && /app/gradlew --max-workers=6 \
   && /app/gradlew catguild-gateway:dependencies
COPY . /app
WORKDIR /app
RUN ./gradlew catguild-gateway:clean  \
   && ./gradlew catguild-gateway:build

# 二阶段构建运行环境
#FROM gcr.io/distroless/java17
FROM openjdk:17-jdk
COPY --from=build-env /app/catguild-gateway/build/libs/catguild-gateway-1.0-SNAPSHOT.jar /app/main.jar
WORKDIR /app
EXPOSE 80
ENTRYPOINT ["java","-Xms64m","-Xmx128m","-XX:+UseZGC","-Dfile.encoding=UTF8","-Duser.timezone=GMT+08","-jar","main.jar"]
CMD ["--spring.profiles.active=prod"]
# 健康检查
#HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
#            CMD curl --silent --fail --request GET http://localhost:8944/actuator/health \
#                | jq --exit-status '.status == "UP"' || exit 1
