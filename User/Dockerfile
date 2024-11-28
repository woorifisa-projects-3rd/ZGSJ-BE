# Build stage
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# 모든 파일을 한번에 복사
COPY . .

# Gradle 권한 설정
RUN chmod +x ./gradlew

# 빌드 실행
RUN ./gradlew build -x test

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/User-0.0.1-SNAPSHOT.jar app.jar

# 불필요한 캐시 파일들 제거
RUN rm -rf /var/cache/* && \
    rm -rf /tmp/* && \
    rm -rf /var/cache/apk/*

EXPOSE 7070
ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m", "-jar", "app.jar"]