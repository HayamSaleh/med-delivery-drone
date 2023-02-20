# Drone Medication Delivery Spring Boot Application

This project aims to develop a drone that can deliver medications to patients. It is a Spring Boot application that uses H2 database and Swagger2 with Swagger-UI for API documentation.


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


##Technologies Used
* Spring Boot
* Swagger2
* Swagger-UI
* H2 database

## Cloning and Running the application locally

*Clone the repository*

```shell
git clone https://github.com/HayamSaleh/med-delivery-drone.git
```

*Build the application*

```shell
cd droneDeliverySystem
mvn clean install
```

*Run the application*

```shell
java -jar target/med-delivery-drone-0.0.1-SNAPSHOT.jar
```
The application should now be running on http://localhost:8080.


**Use Swagger-UI to interact with the APIs**

Open a web browser and navigate to http://localhost:8080/swagger-ui.html.


## Unit Testing

The project has sample unit tests. To run the unit tests, use the following command:

```shell
mvn test
```
