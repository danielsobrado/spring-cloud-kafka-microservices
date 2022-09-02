FROM eclipse-temurin:17-jdk-alpine as development

# The fact that we are using a parent pom.xml file makes the build process a bit more complex.
# A better option could be to have a local Nexus Repository with all the dependencies.
WORKDIR /app
RUN apk add git
RUN git clone -b cloud-lb --single-branch https://github.com/danielsobrado/spring-cloud-kafka-microservices.git
RUN chmod -R 777 spring-cloud-kafka-microservices
WORKDIR /app/spring-cloud-kafka-microservices

# Enabling BuildKit cache for faster builds
RUN --mount=type=cache,target=.m2 ./mvnw install -pl .,jvmcc-product-reactive-service -DskipTests

# Trick to invalidate the cache when the github repo gets updated
ARG USER=danielsobrado
ARG REPO=spring-cloud-kafka-microservices
ARG BRANCH=main
ADD https://api.github.com/repos/$USER/$REPO/git/refs/heads/$BRANCH version.json
RUN ./mvnw install -pl .,jvmcc-product-reactive-service -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/spring-cloud-kafka-microservices/jvmcc-product-reactive-service/target/jvmcc-product-reactive-service-*.jar /jvmcc-product-reactive-service.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-product-reactive-service.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
RUN apk add curl
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-product-reactive-service .
# docker push dalamar/jvmcc-product-reactive-service
# docker run -d -p 8091:8091 --name="jvmcc-product-reactive-service" jvmcc-product-reactive-service