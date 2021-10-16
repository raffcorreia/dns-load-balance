FROM openjdk:8-jre-alpine

# RUN apt-get update \
#   && apt-get install -y \
#   iputils-ping \
#   dnsutils \
#   vim

COPY target/be-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090/tcp
EXPOSE 9090/udp

ENTRYPOINT ["java","-jar","/app.jar"]