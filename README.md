# Spring Cloud Microservices Demo

Example of a Microservices Deployment using Spring Cloud

## Microservices Architecture

### API Gateway

A single point of entry to the application is offered via API Gateway. It routes the incoming request to the proper microservices. The user can't tell that they are being redirected. Consequently, the user is able to access the program using the same url.

#### Advantages

#### Disadvantages

### Service discovery Server

Each microservice registers with the service discovery server so that other microservices can find it.

### Spring Cloud Config Server

* HTTP resource-based API for external configuration (name-value pairs or equivalent YAML content)
* Encrypt and decrypt property values (symmetric or asymmetric)
* Embeddable easily in a Spring Boot application using @EnableConfigServer

## High Volume and Low Latency

We can structure the program in terms of data flows and the propagation of change via them with the aid of reactive programming. This can therefore help us achieve more concurrency with improved resource utilization in a completely non-blocking environment.

Instead of using the blocking sychronous RestTemplate client, we'll use the asynchoronous WebClient API that was added in Spring 5 to obtain JSON data from the URL.

Use the OpenTelemetry API to measure Java performance.

## High Availability

## Resilience

## Security

* Fix security vulnerabilities in our Java image.
* Run as non-root user for security purposes.
* Escape HTML characters in the input of the REST API by using org.apache.commons.commons-text [StringEscapeUtils] (<https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/StringEscapeUtils.html>)
* Escape of SQL is managed by JPA.
* Recommended to review the [ESAPI](<https://github.com/ESAPI/esapi-java-legacy>) project by OWASP.

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

Using JaCoco to generate a a code coverage report.

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

## Notes

I prefer to utilize specific versions in Docker rather than the latest as a best practice to prevent reproducibility problems.
