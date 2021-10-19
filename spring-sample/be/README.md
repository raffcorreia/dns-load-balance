# build the Docker image.
`docker build -t be-dns .`

# Run the container in background, using the same Docker network created
`docker run -d --net=dns-load-balance-net --ip=172.0.0.3 --dns=172.0.0.2 -p 0.0.0.0:9090:9090 --name=be-dns-3 be-dns`
`docker run -d --net=dns-load-balance-net --ip=172.0.0.4 --dns=172.0.0.2 -p 0.0.0.0:9094:9090 --name=be-dns-4 be-dns`
`docker run -d --net=dns-load-balance-net --ip=172.0.0.5 --dns=172.0.0.2 -p 0.0.0.0:9095:9090 --name=be-dns-5 be-dns`

# Start container after stopped
`docker start be-dns-3`

# run application if not started with the container
`docker exec -d be-dns-3 java -jar /app.jar`