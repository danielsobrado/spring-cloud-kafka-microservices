FROM eclipse-temurin:17-jdk-alpine as development
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
COPY src/ src
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/target/jvmcc-review-service-*.jar /jvmcc-review-service.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-review-service.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-review-service .
# docker push dalamar/jvmcc-review-service
# docker run -d -p 8082:8082 --name="jvmcc-review-service" jvmcc-review-service