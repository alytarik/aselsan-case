# build step for spring boot application using gradle

FROM gradle:8.3.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# run step for spring boot application using gradle

FROM openjdk:17-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/aselsan-backend.jar
ENTRYPOINT ["java","-jar","/app/aselsan-backend.jar"]