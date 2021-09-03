FROM adoptopenjdk/openjdk11
MAINTAINER jackWu <627521884@qq.com>

COPY ./target/spring-cloud-alibaba.jar spring-cloud-alibaba.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/spring-cloud-alibaba.jar","&"]