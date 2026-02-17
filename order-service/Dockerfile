# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file built by Maven into the container
# This assumes your jar is in target/ folder
COPY target/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Command to run the Spring Boot jar
ENTRYPOINT ["java","-jar","app.jar"]
