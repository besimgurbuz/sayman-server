FROM openjdk:15-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sayman-server.jar
ENTRYPOINT ["java", "-jar", "./sayman-server.jar"]
