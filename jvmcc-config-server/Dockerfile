FROM eclipse-temurin:17-jdk-alpine as development

# The fact that we are using a parent pom.xml file makes the build process a bit more complex.
# A better option could be to have a local Nexus Repository with all the dependencies.
WORKDIR /app
RUN apk add git curl
RUN git clone -b v1.1 --single-branch https://github.com/danielsobrado/spring-cloud-kafka-microservices.git
RUN chmod -R 777 spring-cloud-kafka-microservices
WORKDIR /app/spring-cloud-kafka-microservices

# Enabling BuildKit cache for faster builds
RUN --mount=type=cache,target=.m2 ./mvnw install -pl .,jvmcc-config-server -DskipTests

# Trick to invalidate the cache when the github repo gets updated
ARG USER=danielsobrado
ARG REPO=spring-cloud-kafka-microservices
ARG BRANCH=main
ADD https://api.github.com/repos/$USER/$REPO/git/refs/heads/$BRANCH version.json
RUN ./mvnw install -pl .,jvmcc-config-server -DskipTests

FROM eclipse-temurin:17-jdk-alpine as build
COPY --from=development /app/spring-cloud-kafka-microservices/jvmcc-config-server/target/jvmcc-config-server-*.jar /jvmcc-config-server.jar
RUN ls -la
RUN java -Djarmode=layertools -jar jvmcc-config-server.jar extract

FROM eclipse-temurin:17-jdk-alpine as production
RUN apk add curl
COPY --from=build dependencies/ ./
COPY --from=build snapshot-dependencies/ ./
COPY --from=build spring-boot-loader/ ./
COPY --from=build application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

# docker build -t dalamar/jvmcc-config-server .
# docker push dalamar/jvmcc-config-server
# docker run -d -p 8888:8888 --name="jvmcc-config-server" jvmcc-config-server