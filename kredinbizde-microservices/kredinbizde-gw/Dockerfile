FROM openjdk:21-jdk
ADD target/kredinbizde-gw.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]