# build the Docker image.
`docker build -t be-dns .`

# Run the container in background, using the same Docker network created
`docker run -d --name=be-dns-3 --net=dns-load-balance-net --ip=172.2.0.3 --dns=127.2.0.2 -p 9090:9090 be-dns`

# Start container after stopped
`docker start be-dns-3`