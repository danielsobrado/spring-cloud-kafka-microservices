FROM eclipse-temurin:17-jdk-alpine as base

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo
EXPOSE 8082
COPY --from=build /app/target/jvmcc-review-crud-*.jar /jvmcc-review-crud.jar
CMD ["./mvnw", "spring-boot:run"]

# docker build -t jvmcc-review-crud .
# docker run -d -p 8082:8082 --name="jvmcc-review-crud" jvmcc-review-crud