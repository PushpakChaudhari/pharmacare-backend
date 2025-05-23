# Use Java 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file built by Maven
COPY target/PharmacareApplication-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
