FROM eclipse-temurin:17-jdk-alpine as development
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
COPY src/ src
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/target/jvmcc-review-reactive-service-*.jar /jvmcc-review-reactive-service.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-review-reactive-service.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-review-reactive-service .
# docker push dalamar/jvmcc-review-reactive-service
# docker run -d -p 8082:8082 --name="jvmcc-review-reactive-service" jvmcc-review-reactive-service