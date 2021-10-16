FROM openjdk:8-jdk

RUN apt-get update \
  && apt-get install -y \
  iputils-ping \
  dnsutils \
  vim
  
COPY target/interaction-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080/tcp
EXPOSE 8080/udp

ENV beHost http://test-be.com:9090

ADD resolv.conf /tmp/resolv.conf

RUN echo "networkaddress.cache.ttl=0" >> $JAVA_HOME/jre/lib/security/java.security

# ENTRYPOINT ["java","-jar","/app.jar"]
# CMD ["/bin/bash", "-c", "cp -f /tmp/resolv.conf /etc/resolv.conf; java -jar /app.jar"]
CMD ["sh", "-c", "cp -f /tmp/resolv.conf /etc/resolv.conf; java -jar /app.jar"]