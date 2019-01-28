# Webtrekk Email Service #

## Technologies Used ###

* Java 8
* Spring Boot
* Spring Kafka

## Execution ##

### Pre-Requisites ###

 - Make sure Zookeeper & Kafka are running
 - Update SMTP properties `spring.mail.username` and `spring.mail.password`
 - Update Kafka Bootstrap server address `spring.kafka.bootstrapAddress` with appropriate value
 
### Additional Execution Information ###

* Max Retries and Retry Interval can be configured with properties - `spring.kafka.max.redelivery.attempts`, `spring.kafka.max.redelivery.interval`

### Application Start-up Instructions ###
Application can be started in following ways:
* From the any Eclipse/IntelliJ IDE, directly execute the Main class (WebtrekkEmailApplication.java)
* From Spring Tool Site(STS) IDE simply right click and run as Spring Boot App.
* And to execute from Command line use the either mvn exec:java or mvn spring-boot:run commands

## REST API Endpoints ##

* API Endpoint : http://localhost:8080/asyncEmail

## API Documentation (Swagger) URL ##

http://localhost:8080/swagger-ui.html
