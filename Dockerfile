FROM eclipse-temurin:21
COPY libs/pipeline-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]