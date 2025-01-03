FROM maven:3.9.5-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build /app/target/progress-tracker-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]









## Stage 1: Build the application
#FROM maven:3.9.5-eclipse-temurin-21 AS build
#
## Set the working directory for the build stage
#WORKDIR /app
#
## Copy the Maven configuration file (pom.xml) and download dependencies
#COPY pom.xml ./
#RUN mvn dependency:resolve
#
## Copy the source code into the container
#COPY src ./src
#
## Build the application (skip tests for faster builds)
#RUN mvn clean package -DskipTests
#
## Stage 2: Create a runtime image with JDK
#FROM eclipse-temurin:21-jre-alpine
#
## Set the working directory for the runtime container
#WORKDIR /app
#
## Copy the JAR file from the build stage
#COPY --from=build /app/target/progress-tracker-api-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the application port (usually 8080 for Spring Boot)
#EXPOSE 8080
#
## Command to run the application
#ENTRYPOINT ["java", "-jar", "app.jar"]
