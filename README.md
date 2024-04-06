# Patika Bootcamp

### Table of contents

- [PROJECT STRUCTURE](#Project-architecture)
- [TECHNOLOGY STACK](#technology-stack)
- [DOCKER](#docker)
- [FEATURES](#features)
- [CONTACT](#contact)

### Project Architecture
![Project Design](https://raw.githubusercontent.com/ipekcill/image-resources/main/Project-structure_drawing.png)

### Technology Stack
- Java 21
- Spring Boot
- Spring Cloud (Eureka Discovery,Eureka Server, Gateway)
- Spring Data, Hibernate
- Maven
- RabbitMQ
- Kafka
- PostgreSql
- Feign Client
- MongoDB
- Redis
- Docker
- JUnit
- Swagger

### Features
All components have been Dockerized.
#### Micro Services:
- KredinBizde Service:
    - ........
  
  All unit tests successfully run:
      ![UnitTests](https://raw.githubusercontent.com/ipekcill/image-resources/main/unitTests.png)
- Garanti Service:
    - ........
- Notification Service:
    - ........
- Log Service:
    - ........
#### Service Registry:
- All above services should Register them with this Registry
#### API Gateway:
- All Services should be accessible with single gateway

### Docker
All Kredinbizde microservices and related components are collected in docker-compose.yml. Applications were containerized.
This build can be easily started with the `docker-compose up` command. Once all services start running in Docker, you can access Swagger from [here](http://localhost:8083/swagger-ui/index.html).


### Contact
[Linkedin](https://www.linkedin.com/in/ipekcil/)

ipekcill94@gmail.com