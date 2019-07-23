# Spring Boot, JPA, Hibernate Rest API Example for Musala

Build Restful CRUD API for a simple crud gateways application using Spring Boot, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/rmbartolome/musala-gateways.git
```
**2. Build and run the app using maven**

```bash
mvn package
java -jar target/gateways-musala-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    Gateways
    GET /api/gateways
    
    POST /api/gateway
    
    GET /api/gateway/{id}
    
    PUT /api/gateway/{id}
    
    DELETE /api/gateway/{id}
    
    Device
    DELETE /api/device/{?gatewayId&nameDevice}

You can test them using postman or any other rest client.
