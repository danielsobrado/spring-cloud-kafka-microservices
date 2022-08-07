FROM eclipse-temurin:17-jdk-alpine as development
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
COPY src/ src
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/target/jvmcc-review-crud-*.jar /jvmcc-review-crud.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-review-crud.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-review-crud .
# docker push dalamar/jvmcc-review-crud
# docker run -d -p 8082:8082 --name="jvmcc-review-crud" jvmcc-review-crud