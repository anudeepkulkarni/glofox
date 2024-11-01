FROM openjdk:17-jdk-alpine
COPY target/glofox-app.jar glofox-app.jar
ENTRYPOINT ["java", "-jar", "/glofox-app.jar"]
