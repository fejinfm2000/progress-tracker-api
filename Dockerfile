FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar progress-tracker-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","progress-tracker-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080