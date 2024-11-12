ARG BUILD_HOME=/build

FROM gradle:8-jdk-21-and-23 AS build-image

ARG BUILD_HOME
WORKDIR $BUILD_HOME

COPY --chown=gradle:gradle build.gradle settings.gradle ./
COPY --chown=gradle:gradle src ./src

RUN gradle --no-daemon build

FROM eclipse-temurin:21

ARG BUILD_HOME
ENV BUILD_HOME=$BUILD_HOME
WORKDIR /app

# App name is dependant on the version
COPY --from=build-image $BUILD_HOME/build/libs/pr4-direct-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]