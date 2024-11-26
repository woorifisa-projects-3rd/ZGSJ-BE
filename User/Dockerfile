# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY build/libs/User-0.0.1-SNAPSHOT.jar app.jar

RUN rm -rf /var/cache/* && \
   rm -rf /tmp/* && \
   rm -rf /var/cache/apk/*

EXPOSE 7070
ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m", "-jar", "app.jar"]