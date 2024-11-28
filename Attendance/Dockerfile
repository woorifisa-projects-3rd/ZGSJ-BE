FROM gradle:7.6.1-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

# Run stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 6060
ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m", "-jar", "app.jar"]