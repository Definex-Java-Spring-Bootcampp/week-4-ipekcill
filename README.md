# Patika Bootcamp

### Table of contents

- [PROJECT STRUCTURE](#project-architecture)
- [TECHNOLOGY STACK](#technology-stack)
- [DOCKER](#docker)
- [FEATURES](#features)
- [ENTITY RELATIONSHIP](#entity-relationship)
- [CONTACT](#contact)

### Project Architecture
![Project Design](https://raw.githubusercontent.com/ipekcill/image-resources/main/structure.jpg)

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
Environment-centric configuration was implemented. Each microservice has both application.yml and application-docker.yml files. All components have been Dockerized. Running it in a Docker environment ensures the application-docker.yml configurations are applied.
#### Micro Services:

- KredinBizde Service:
  - Supports auditable entity classes.
  - Utilizes caching to improve performance by storing frequently accessed data in memory.
  - Utilizes Feign Client for communicating with the Garanti Service to create applications.
  - Implements asynchronous notification using RabbitMQ to send notifications.
  - Implements logging with Kafka to collect exceptions and stores them in MongoDB for further analysis and monitoring.
  - Implements custom exception classes to handle various error scenarios
  - Includes unit tests to ensure the correctness and reliability of the codebase.
  
    All unit tests successfully run:

![UnitTests](https://raw.githubusercontent.com/ipekcill/image-resources/main/unitTests.png)

- Garanti Service:
  - KredinBizde Service integrates with the Garanti Service for synchronously communicating  applications from KredinBizde Service to the Garanti Bank. 
  - Implements custom exception classes to handle various error scenarios
  
- Notification Service:
  - Implements the Strategy Pattern to provide a flexible and extensible way of sending notifications. 
  - The Strategy Pattern allows to support multiple notification strategies (e.g., email, SMS, mobile) and switch between them dynamically at runtime.

- Log Service:
  - Responsible for generating exception logs and storing them in MongoDB using Kafka for message queuing.
  
#### Service Registry:
- All above services should Register them with this Registry
#### API Gateway:
- All Services should be accessible with single gateway

### Docker
All Kredinbizde microservices and related components are collected in docker-compose.yml. Applications were containerized.
This build can be easily started with the `docker-compose up` command. Once all services start running in Docker, you can access Swagger from [here](http://localhost:8083/swagger-ui/index.html).

Swagger documentation example :
![Example end-point](https://raw.githubusercontent.com/ipekcill/image-resources/main/Screenshot_1.png)

### Entity Relationship
#### Entity Relationship and Database Tables

![Diagram](https://raw.githubusercontent.com/ipekcill/image-resources/main/entity.drawio.last.png)


### Contact
[Linkedin](https://www.linkedin.com/in/ipekcil/)

ipekcill94@gmail.com
