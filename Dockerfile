FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

# Grant executable permissions to the gradlew script
RUN chmod +x ./gradlew

# Build the application using Gradle
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/shipper-connect.jar shipper-connect-app.jar

ENTRYPOINT ["java", "-jar", "shipper-connect-app.jar"]
