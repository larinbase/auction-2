# BUILD
FROM gradle:7.5.1-jdk17 AS builder
WORKDIR /build
COPY . .
RUN gradle clean build -x test

# IMAGE
FROM openjdk
EXPOSE 8080
WORKDIR /opt/app
# JAR
COPY --from=builder /build/build/libs/*.jar app.jar
# IMG
COPY --from=builder /build/src/main/resources/static/img /opt/app/img
ENTRYPOINT ["java", "-jar", "app.jar"]
