# Use official OpenJDK 17 image
FROM openjdk:17.0.2-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built jar file from target folder
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
