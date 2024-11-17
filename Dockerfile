FROM eclipse-temurin:21-jdk-alpine as builder
COPY . .
COPY ./mvnw clean install -DskipTests
ENTRYPOINT ["java", "-jar", "target/email-sender-1.0.jar"]

