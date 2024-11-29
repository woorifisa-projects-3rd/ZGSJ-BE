FROM gradle:7.6.1-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Xms256m", "-Xmx2048m", "-jar", "app.jar"]