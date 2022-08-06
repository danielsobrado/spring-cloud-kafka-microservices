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
EXPOSE 8761
COPY --from=build /app/target/jvmcc-service-discovery-*.jar /jvmcc-service-discovery.jar
CMD ["./mvnw", "spring-boot:run"]

# docker build -t dalamar/jvmcc-service-discovery .
# docker push dalamar/jvmcc-service-discovery
# docker run -d -p 8082:8082 --name="jvmcc-service-discovery" jvmcc-service-discovery