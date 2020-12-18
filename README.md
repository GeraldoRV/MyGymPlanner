# MyGymPlanner

This project have two modules: Frontend, that was generated with [Angular CLI](https://github.com/angular/angular-cli), 
and Backend, that was generated with [Spring Boot](https://spring.io/projects/spring-boot)

## Requirements

For building and running the application you need:

- [JDK 1.8 +](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)
- [Node.js](https://nodejs.org/en/)

## Run the project
To execute it is necessary to execute the two modules separately, in this case, the frontend will be using port `4200` and the backend the `8080`.

To run on a single port (`8080`), you would have to build the frontend with `ng build`  and then deploy the back with `mvn clean install`, 
which will generate the jar file in the `backend/target/` directory. 
Now you can run the application from the backend module with ` mvn spring-boot:run`

## Configuration database

Set up the connection with the database in the file `main.resources.aplication.properties` and
 `test.resources.aplication-test.properties` for testing in the backend module.
The data of the database when executing is generated from the `main.dao.DBSeeder` class.


## Further help

To get more help on Spring Boot go check out the [Spring Boot](https://spring.io/projects/spring-boot) documentation
and for Angular check out [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
