# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration file to leverage Docker's caching
COPY pom.xml ./

# Pre-fetch dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/progress-tracker-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
