FROM eclipse-temurin:17-jdk-alpine as development

# The fact that we are using a parent pom.xml file makes the build process a bit more complex.
# A better option could be to have a local Nexus Repository with all the dependencies.
WORKDIR /app
RUN apk add git
RUN git clone https://github.com/danielsobrado/spring-cloud-kafka-microservices.git
RUN chmod -R 777 spring-cloud-kafka-microservices
WORKDIR /app/spring-cloud-kafka-microservices

# Enabling BuildKit cache for faster builds
RUN --mount=type=cache,target=.m2 ./mvnw install -pl .,jvmcc-review-reactive-service -DskipTests

# Trick to invalidate the cache when the dependencies change
ARG USER=danielsobrado
ARG REPO=spring-cloud-kafka-microservices
ARG BRANCH=main
ADD https://api.github.com/repos/$USER/$REPO/git/refs/heads/$BRANCH version.json
RUN ./mvnw install -pl .,jvmcc-review-reactive-service -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/spring-cloud-kafka-microservices/jvmcc-review-reactive-service/target/jvmcc-review-reactive-service-*.jar /jvmcc-review-reactive-service.jar
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