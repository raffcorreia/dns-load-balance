# Create test network

`docker network create --subnet=172.2.0.0/16 dns-load-balance-net`

# build the Docker image.
`docker build -t bind9 .`

# Run the container in background, using the same Docker network created:
`docker run -d --rm --name=dns-server --net=dns-load-balance-net --ip=172.2.0.2 bind9`

# enable the bind9 daemon:
`docker exec -d dns-server /etc/init.d/bind9 start`

# Update password
It is not needed: `docker exec -itu root dns-server passwd`

# Connect to the container
`docker exec -it dns-server bash`

# Install ping
`apt-get update`
`apt-get install iputils-ping`