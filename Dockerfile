# First stage: Build the application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/tusharSCM-0.0.1-SNAPSHOT.jar tusharSCM.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "tusharSCM.jar"]
