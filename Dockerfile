FROM maven:3.6.3-openjdk-15 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/sayman-server-0.0.1-SNAPSHOT.jar /usr/app/sayman-server-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/app/sayman-server-0.0.1-SNAPSHOT.jar"]
