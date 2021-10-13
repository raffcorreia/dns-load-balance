FROM openjdk:8-jre-slim

COPY target/interaction-api-0.0.1-SNAPSHOT.jar

ENV beHost http://localhost:9090

ENTRYPOINT ["java","-jar","/app.jar"]