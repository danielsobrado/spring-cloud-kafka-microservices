FROM eclipse-temurin:17-jdk-alpine as development

# The fact that we are using a parent pom.xml file makes the build process a bit more complex.
# A better option could be to have a local Nexus Repository with alll the dependencies.
WORKDIR /app
COPY mvnw ./
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
COPY . .
RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/jvmcc-review-service/target/jvmcc-review-service-*.jar /jvmcc-review-service.jar
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
# docker run -d -p 8091:8091 --name="jvmcc-review-service" jvmcc-review-service