FROM openjdk:21-jdk
ADD target/kredinbizde-discovery.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]