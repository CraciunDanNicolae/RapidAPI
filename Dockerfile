FROM maven:3-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk
COPY --from=build /target/rapid-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx400m", "-jar", "app.jar"]