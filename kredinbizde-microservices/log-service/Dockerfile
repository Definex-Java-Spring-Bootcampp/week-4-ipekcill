FROM openjdk:21-jdk
ADD target/log-service.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]