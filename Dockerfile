FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/AuthService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3333

ENTRYPOINT ["java","-jar","app.jar"]
