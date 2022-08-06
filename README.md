## Spring Cloud Kafka Microservices
Example of a Docker Microservices Deployment using Spring Cloud

## Microservices Architecture

### API Gateway

A single point of entry to the application is offered via API Gateway. It routes the incoming request to the proper microservices. The user can't tell that they are being redirected. Consequently, the user is able to access the program using the same url.

##### Advantages
##### Disadvantages

### Service discovery Server
Each microservice registers with the service discovery server so that other microservices can find it.

## High Volume and Low Latency

Use the OpenTelemetry API to measure Java performance

## High Availability
## Resilience

## Security

* Fix security vulnerabilities in our Java image.
* Run as non-root user for security purposes.

## Cache

## Database

Based on how this data will be used, a SQL or NoSQL database will be selected. Consistency is crucial, therefore we'll favor a SQL database and ACID compliance for complex queries and transactions.

We will favor NoSQL for large volumes of data that are not transactional in nature. The BASE model will be applicable.

Start MySQL Server for testing:
```bash
docker run -p 3306:3306 --name mysql-server \ 
 -e MYSQL_ROOT_PASSWORD=root \
 -e MYSQL_USER=demo \
 -e MYSQL_PASSWORD=demo \
 -d mysql/mysql-server:8.0
 ```

## Testing

Using JaCoco to generate a a code coverage report.
## Build and Deployment

[Spring initializr](https://start.spring.io/) has been used to generate the initial projects.

Use the the [Snyk Extension for Docker Desktop](https://snyk.io/blog/docker-desktop-with-snyk-and-new-docker-vulnerability-cheat-sheet/) to inspect our Spring Boot application, this [extension](https://docs.snyk.io/ide-tools/visual-studio-code-extension-for-snyk-code) is also available for VS Code, Eclipse and IntelliJ.

Use docker buildx command to help you build multi-architecture images, in this case we'll build linux/amd64.

It is safer to run apps with user privileges because it helps to reduce hazards. Docker containers fall under the same category. Docker containers and the running apps inside of them have root access by default. Running Docker containers as non-root users is therefore recommended.

#### Base Spring Boot Image

Since the JRE is no longer provided by the upstream OpenJDK image, no official JRE images are created. The "vanilla" builds of Oracle's OpenJDK are all that are included in the official OpenJDK images.

The Eclipse Temurin project offers procedures and code to facilitate the production of runtime binaries. These are cross-platform, enterprise-grade, and high performance.

#### Multi-Stage Builds

A Docker build can use a single base image for compilation, packaging, and unit testing when using multi-stage builds.

The application runtime is stored in a different image. The finished image is safer and smaller as a result.