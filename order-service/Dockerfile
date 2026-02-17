# Use OpenJDK 17 slim image
FROM openjdk:17.0.2-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file built by Maven into the container
# This assumes your jar is in target/ folder
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Command to run the Spring Boot jar
ENTRYPOINT ["java","-jar","app.jar"]
