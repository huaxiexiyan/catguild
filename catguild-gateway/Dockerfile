# 一阶段编译打包
FROM openjdk:17-jdk-slim AS build-env
RUN ls
ADD ./setting.gradle ./build.gradle ./gradlew ./gradle /app/
RUN chmod +x ./gradlew \
   && ./gradlew --max-workers=6 \
   && ./gradlew catguild-gateway:dependencies  \
COPY . /app
WORKDIR /app
RUN chmod +x ./gradlew \
   && ./gradlew --max-workers=6 \
   && ./gradlew catguild-gateway:clean  \
   && ./gradlew catguild-gateway:build

# 二阶段构建运行环境
#FROM gcr.io/distroless/java17
FROM openjdk:17-jdk
COPY --from=build-env /app/catguild-gateway/build/libs/catguild-gateway-1.0-SNAPSHOT.jar /app/main.jar
WORKDIR /app
EXPOSE 80
ENTRYPOINT ["java","-Xms64m","-Xmx128m","-Dfile.encoding=UTF8","-Duser.timezone=GMT+08","-jar","main.jar"]
CMD ["--spring.profiles.active=prod"]
# 健康检查
#HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
#            CMD curl --silent --fail --request GET http://localhost:8944/actuator/health \
#                | jq --exit-status '.status == "UP"' || exit 1
