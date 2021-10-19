# build the Docker image.
`docker build -t interaction-api .`

# Run the container in background, using the same Docker network created
`docker run -d --name=interaction-api --net=dns-load-balance-net --ip=172.0.0.254 --dns=172.0.0.2 -p 8080:8080 interaction-api`

# Start container after stopped
`docker start be-dns-3`