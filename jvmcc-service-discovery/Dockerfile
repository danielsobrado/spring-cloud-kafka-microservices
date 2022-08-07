FROM eclipse-temurin:17-jdk-alpine as development
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
COPY src/ src
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/target/jvmcc-service-discovery-*.jar /jvmcc-service-discovery.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-service-discovery.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-service-discovery .
# docker push dalamar/jvmcc-service-discovery
# docker run -d -p 8761:8761 --name="jvmcc-service-discovery" jvmcc-service-discovery