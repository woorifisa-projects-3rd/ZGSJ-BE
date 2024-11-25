FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY build/libs/API-Gateway-0.0.1-SNAPSHOT.jar app.jar

RUN rm -rf /var/cache/* && \
   rm -rf /tmp/* && \
   rm -rf /var/cache/apk/*

EXPOSE 8888
ENTRYPOINT ["java", "-Xms256m", "-Xmx1024m", "-jar", "app.jar"]