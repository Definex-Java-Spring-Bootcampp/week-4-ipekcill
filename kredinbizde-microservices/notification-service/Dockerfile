FROM openjdk:21-jdk
ADD target/notification-service.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]