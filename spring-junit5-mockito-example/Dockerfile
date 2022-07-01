FROM maven:3.6.3 AS maven
LABEL MAINTAINER="htyesilyurt@gmail.com"

WORKDIR /opt/spring-test-example
COPY . /opt/spring-test-example
RUN mvn clean install 

FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=spring-test-example.jar

WORKDIR /opt/spring-test-example

COPY --from=maven /opt/spring-test-example/target/${JAR_FILE} /opt/spring-test-example/

ENTRYPOINT ["java","-jar","spring-test-example.jar"]