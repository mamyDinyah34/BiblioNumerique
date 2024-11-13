FROM openjdk:22-jdk-slim
COPY target/Admin-0.0.1-SNAPSHOT.jar Admin.jar
ENTRYPOINT ["java", "-jar", "/Admin.jar"]
