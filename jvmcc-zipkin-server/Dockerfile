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
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
EXPOSE 9411
COPY --from=build /app/target/jvmcc-zipkin-server-*.jar /jvmcc-zipkin-server.jar
CMD ["./mvnw", "spring-boot:run"]

# docker build -t dalamar/jvmcc-zipkin-server .
# docker push dalamar/jvmcc-zipkin-server
# docker run -d -p 9411:9411 --name="jvmcc-zipkin-server" jvmcc-zipkin-server