FROM ubuntu:bionic

RUN apt-get update \
  && apt-get install -y \
  bind9 \
  bind9utils \
  bind9-doc

RUN apt-get install -y \
  iputils-ping \
  dnsutils \
  vim

EXPOSE 53/tcp
EXPOSE 53/udp

# Enable IPv4
RUN sed -i 's/OPTIONS=.*/OPTIONS="-4 -u bind"/' /etc/default/bind9

# Copy configuration files
COPY named.conf.options /etc/bind/
COPY named.conf.local /etc/bind/
COPY db.test-be.com /etc/bind/zones/

ADD resolv.conf /tmp/resolv.conf    

# Run eternal loop
# CMD ["/bin/bash", "-c", "cp -f /tmp/resolv.conf /etc/resolv.conf; while :; do sleep 10; done"]
# CMD ["/bin/bash", "-c", "/etc/init.d/bind9 start"]
# CMD ["/bin/bash", "-c", "while :; do sleep 1000; done"]
# ENTRYPOINT ["/bin/bash", "-c", "/etc/init.d/bind9 start"]
CMD ["/bin/bash", "-c", "/etc/init.d/bind9 start; while :; do sleep 1000; done"]