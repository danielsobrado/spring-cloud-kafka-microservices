# Spring Cloud Microservices Demo

Example of a Microservices Deployment using Spring Cloud.We implement two microservices, one that collects product reviews from a third-party website and another that combines all the reviews related to a given product.

The system's security, high reliability, ability to handle a large number of requests, and consideration of the database as a bottleneck that should be impacted as little as possible are its critical aspects.

## Microservices Architecture

Reactive and non-reactive strategies are two ways we might approach this use case.

We'll use REST APIs to interface between the services in a non-reactive manner, and we'll store the data in a MySQL database.

From a reactive standpoint, we could combine Spring WebFlux with Cloud Streaming using a Kafka Middleware, and a MongoDB Database with ready-to-use reactive drivers for Spring Boot to store the data.

Using a Microservices-based design has some disadvantages:

* Communication between services is difficult to manage and is complicated:
  * Temporary unavailability of the service
  * API contract maintenance
  * Eventual consistency
* It can be quite difficult to debug.
* Microservices are often highly expensive.
* The very worst thing you can do is develop a distributed monolith if you don't know how microservices should be built.

### API Gateway

A single point of entry to the application is offered via API Gateway. It routes the incoming request to the proper microservices. The user can't tell that they are being redirected. Consequently, the user is able to access the program using the same url.

We'll follow the following steps:

* Start Eureka server(s)
* Start Review servise(s)
* Start Product service(s)
* Start App Gateway
  
  ```bash
    java -Dapp_port=443 -jar .\target\jvmcc-api-gateway-1.0.jar
  ```

Securing a Spring Cloud Gateway application acting as a resource server is no different from a regular resource service.

Note: [SSLScan](https://github.com/rbsec/sslscan) can be used to verify the ciphers that are allowed.

### Service discovery Server

Each microservice registers with the service discovery server so that other microservices can find it.

### Spring Cloud Config Server

Spring's client/server approach for storing and serving distributed configurations across many apps and contexts is known as Spring Cloud Config.

This configuration store can be changed during program runtime and is ideally versioned under Git version control.

* HTTP resource-based API for external configuration (name-value pairs or equivalent YAML content)
* Encrypt and decrypt property values (symmetric or asymmetric)
* Embeddable easily in a Spring Boot application using @EnableConfigServer

## High Volume and Low Latency

We can structure the program in terms of data flows and the propagation of change via them with the aid of reactive programming. This can therefore help us achieve more concurrency with improved resource utilization in a completely non-blocking environment.

Instead of using the blocking sychronous RestTemplate client, we'll use the asynchoronous WebClient API that was added in Spring 5 to obtain JSON data from the URL.

Use the OpenTelemetry API to measure Java performance.

## High Availability

Modularity, redundancy, and monitoring are the foundations of availability; if one service fails, another must be prepared to take over.
The degree of decoupling we can achieve depends on how modular the services are; they should be autonomous, replaceable, and have a clear API.

Understanding when a service is not responding and another service needs to take over requires monitoring.

### Service Discovery High Availability (Eureka Server)

We'll configure Eureka Server as a Cluster for High Availability.

Configure multiple profiles for each server:

```yml
spring:
  profiles: peer-1
  application:
    name: eureka-server-clustered
server:
  port: 9001  
eureka:
  instance:
    hostname: jvmcc-eureka-1-server.com    
  client:
    registerWithEureka: true
    fetchRegistry: true       
    serviceUrl:
      defaultZone: http://jvmcc-eureka-2-server.com:9002/eureka/,http://jvmcc-eureka-3-server.com:9003/eureka/
 ```

Configure Eureka Clients for High Availability:

```yml
eureka:
  client:
    serviceUrl:
      defaultZone: http://jvmcc-eureka-1-server.com:9001/eureka, http://jvmcc-eureka-1-server.com:9002/eureka, http://jvmcc-eureka-1-server.com.com:9003/eureka
```

Test the Eureka server without High Availability:

```bash
java -jar -Dspring.profiles.active=default ./target/jvmcc-service-discovery-1.0.jar
```

Test the Eureka server with High Availability:

```bash
java -jar -Dspring.profiles.active=jvmcc-eureka-1 ./target/jvmcc-service-discovery-1.0.jar
java -jar -Dspring.profiles.active=jvmcc-eureka-2 ./target/jvmcc-service-discovery-1.0.jar
java -jar -Dspring.profiles.active=jvmcc-eureka-3 ./target/jvmcc-service-discovery-1.0.jar
```

You want to raise multiple instances of your micro-services on different ports and register them in Eureka, you could do:

```bash
java -jar .\target\jvmcc-review-service-1.0.jar --server.port=8091
java -jar .\target\jvmcc-review-service-1.0.jar --server.port=8092
java -jar .\target\jvmcc-product-service-1.0.jar --server.port=8094
java -jar .\target\jvmcc-product-service-1.0.jar --server.port=8095
java -jar .\target\jvmcc-product-service-1.0.jar --server.port=8096

```

And see in your browser:

![Eureka High Availability](documentation/Eureka-HA.JPG?raw=true "Eureka High Availability")

Note: When testing in a local machine you might need to configure the ```\etc\hosts``` to find host names in your machine. ( ```C:\Windows\System32\drivers\etc\hosts``` for windows.)

### API Gateway High Availability

We simply need to establish several services and register them with the Service Discovery server to form a cluster in order to achieve High Availability in the Application Gateway.

![API Gateway High Availability](documentation/APIGateway-HA.JPG?raw=true "API Gateway High Availability")

Since API Gateway is stateless and requires a load balancer in front, such as NginX, it cannot be used for external access.

## Resilience

Due to the interdependence of the microservices, we must use a circuit breaker approach to ensure that, in the event of an outage or a cluttered service, we may restart without losing any data.

We'll use [Resilient4J](https://www.baeldung.com/spring-cloud-circuit-breaker) but other approaches can also be used, like Hystrix, Sentinel or Spring Retry.

## Security

Security can be applied multiple levels:

* **OS Vulnerabilities**: Keep OS patched and so frwquent VA scans.
* **Penetration testing for front-end**: Follow OWASP
* **Dependency scan**: for the libraries used in the code/

We are using the following security best practices:

* Fix security vulnerabilities in our Java image.
* Run as non-root user for security purposes.
* Escape HTML characters in the input of the REST API by using org.apache.commons.commons-text [StringEscapeUtils] (<https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/StringEscapeUtils.html>)
* Escape of SQL is managed by JPA.
* Recommended to review the [ESAPI](<https://github.com/ESAPI/esapi-java-legacy>) project by OWASP.

Authentication & Authorization:

* Basic user/password authentication
* JWT tokens / Oauth
* LDAP Server

Communication:

* Use TLS 1.2+, and restrict in Tomcat the Ciphers to secure Ciphers only.

You can create a key with:

```bash
keytool -genkeypair -alias jvmcc -keystore \src\main\resources\jvmcc-keystore.p12 -keypass jvmcc-secret -storeType PKCS12 -storepass jvmcc-secret -keyalg RSA -keysize 2048 -validity 365 -dname "C=MA, ST=ST, L=L, O=jvmcc, OU=jvmcc, CN=localhost" -ext "SAN=dns:localhost"
```

This can be configured at the API Gateway level with:

```yml
server:
  ssl:
    enabled: true
    key-alias: jvmcc
    key-store-password: jvmcc-secret
    key-store: classpath:jvmcc-keystore.p12
    key-store-type: PKCS12
```

**Note**: This will trigger a warning in the browser as the certificate is not signed from a recognized authority, you can upload the certificate locally to avoid this warning.

Depending on the desired level of security, the security can be applied at the microservice level, requiring LDAP and mTLS for each inter-service communication, or it can be delegated to the API Gateway to prevent any unencrypted data flow in the network.

Other advanced security meassures:

* Database activity monitoring (DAM) for privileged use and application access.
* File integrity monitoring (FIM) to avoid file and configuration tampering.

## Cache

The products could be cached in the database if the goal is to decrease the number of calls to external APIs.

In this scenario, we'll approach the database as a limited resource that demands wise utilization.

We need to think about the following:

* The anticipated ratio of writes/updates to readers.
* Which is more crucial: quick writes or quick reads.
* The amount of "cheap" extra disk space that is offered.
* Whether the summary has to be updated immediately or if it can wait.

We'll examine a few possibilities, including:

* Log all reviews in the database.
* Only keep the total amount of reviews for each product in the database.
* Cache the product retrieval for a period of time, if we assume that it doesn't change very often.
* If we suppose that the query of Products is more frequent than the insertion of Reviews, we could use a [Redis](https://www.baeldung.com/spring-boot-redis-cache) distributed cache to store aggregations.

In order to approach our caching strategy, we must make the following assumptions:

* Reviews are occasionally updated but frequently read.
* We cache a product locally because it doesn't change frequently in the third-party API.
* Requests to view product scores outnumber requests to add fresh reviews by a wide margin.

This is something that can develop based on usage and data gathered in a real-world situation.

## Database

Based on how this data will be used, a SQL or NoSQL database will be selected. Consistency is crucial, therefore we'll favor a SQL database and ACID compliance for complex queries and transactions.

We will favor NoSQL for large volumes of data that are not transactional in nature. The BASE model will be applicable.

### Use MySQL

Start MySQL Server for testing:

```bash
docker run -p 3306:3306 --name mysql-server \ 
 -e MYSQL_ROOT_PASSWORD=root \
 -e MYSQL_USER=demo \
 -e MYSQL_PASSWORD=demo \
 -d mysql/mysql-server:8.0
 ```

To view the data in the container you can login by using:

```bash
docker exec -it mysql-server mysql -uroot -p
```

For the purpose of this example, we will create a demo database and some initial data on an init SQL file that will be triggered on the Docker entrypoint file.

## Testing

To prevent large loads of Spring Boot contexts and calls to external APIs during unit testing, we can simply mock the repositories and external REST endpoints.

We have two options for integration testing: either assume that the infrastructure is ready (DB, Middleware...) and create additional schemas for testing during the test cases, or utilize TestContainers to deploy new containers while testing (Assuming that docker is available).

Using JaCoco to generate a code coverage report.

## Build and Deployment

[Spring initializr](https://start.spring.io/) has been used to generate the initial projects.

Use the the [Snyk Extension for Docker Desktop](https://snyk.io/blog/docker-desktop-with-snyk-and-new-docker-vulnerability-cheat-sheet/) to inspect our Spring Boot application, this [extension](https://docs.snyk.io/ide-tools/visual-studio-code-extension-for-snyk-code) is also available for VS Code, Eclipse and IntelliJ.

Use docker buildx command to help you build multi-architecture images, in this case we'll build linux/amd64.

Spring Boot includes both Maven and Gradle support for buildpacks. For example, building with Maven, we would run the command:

```bash
./mvnw spring-boot:build-image
```

It is safer to run apps with user privileges because it helps to reduce hazards. Docker containers fall under the same category. Docker containers and the running apps inside of them have root access by default. Running Docker containers as non-root users is therefore recommended.

### Base Spring Boot Image

Since the JRE is no longer provided by the upstream OpenJDK image, no official JRE images are created. The "vanilla" builds of Oracle's OpenJDK are all that are included in the official OpenJDK images.

The Eclipse Temurin project offers procedures and code to facilitate the production of runtime binaries. These are cross-platform, enterprise-grade, and high performance.

#### Multi-Stage Builds

A Docker build can use a single base image for compilation, packaging, and unit testing when using multi-stage builds.

The application runtime is stored in a different image. The finished image is safer and smaller as a result.

## TODO

* Distributed Tracing [ZipKin](https://github.com/openzipkin/zipkin)

## Notes

I prefer to utilize specific versions in Docker rather than the latest as a best practice to prevent reproducibility problems.
