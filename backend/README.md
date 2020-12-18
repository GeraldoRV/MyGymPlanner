# Backend

This project was generated with [Spring Boot](https://spring.io/projects/spring-boot) version 2.1.3.RELEASE.

## Requirements

For building and running the application you need:

- [JDK 1.8 +](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)


## Run the application

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `main.BackendApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
Navigate to `http://localhost:8080/`. Or with the IP of the machine.

## Configuration database

Set up the connection with the database in the file `main.resources.aplication.properties` and `test.resources.aplication-test.properties` for testing.
The data of the database when executing is generated from the `main.dao.DBSeeder` class.

## Deploy 

Run `mvn clean install` to build the project. The build artifacts will be stored in the `target/` directory.

## Running test 

Run `mvn test ` to run the tests of the project. 

## Further help

To get more help on the Spring Boot go check out the [Spring Boot](https://spring.io/projects/spring-boot) documentation.
