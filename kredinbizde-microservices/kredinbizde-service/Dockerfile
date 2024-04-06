FROM openjdk:21-jdk
ADD target/kredinbizde-service.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]