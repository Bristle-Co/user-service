FROM openjdk:17
MAINTAINER AndersonHsieh
RUN mkdir app
WORKDIR /app
COPY target/user-service-0.0.1.jar app/user-service-0.0.1.jar
ENTRYPOINT ["java","-jar","app/user-service-0.0.1.jar"]