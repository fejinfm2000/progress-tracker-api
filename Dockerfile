# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory for the build stage
WORKDIR /app

# Copy Maven configuration file
COPY pom.xml ./

# Download dependencies (caching them for faster builds in future)
RUN mvn dependency:go-offline

# Copy source code into the container
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Create a runtime image with JRE
FROM eclipse-temurin:17-jre

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/progress-tracker-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (usually 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]


# FROM eclipse-temurin:21-jdk-alpine
# VOLUME /tmp
# COPY target/*.jar progress-tracker-api-0.0.1-SNAPSHOT.jar
# ENTRYPOINT ["java","-jar","progress-tracker-api-0.0.1-SNAPSHOT.jar"]
# EXPOSE 8080

