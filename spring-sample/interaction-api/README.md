# build the Docker image.
`docker build -t interaction-api .`

# Run the container in background, using the same Docker network created
`docker run -d --name=interaction-api --net=dns-load-balance-net --ip=172.0.0.254 --dns=172.0.0.2 -p 8080:8080 interaction-api`

# Start container after stopped
`docker start be-dns-3`


docker stop interaction-api;docker rm interaction-api;docker rmi interaction-api
docker build -t interaction-api .;docker run -d --name=interaction-api --net=dns-load-balance-net --ip=172.0.0.254 --dns=172.0.0.2 -p 8080:8080 interaction-api

docker stop be-dns-3;docker stop be-dns-4;docker stop be-dns-5;docker rm be-dns;docker rmi be-dns
docker build -t be-dns .;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.3 --dns=172.0.0.2 -p 0.0.0.0:9090:9090 --name=be-dns-3 be-dns;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.4 --dns=172.0.0.2 -p 0.0.0.0:9094:9090 --name=be-dns-4 be-dns;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.5 --dns=172.0.0.2 -p 0.0.0.0:9095:9090 --name=be-dns-5 be-dns;\
docker exec -d be-dns-3 java -jar /app.jar;\
docker exec -d be-dns-4 java -jar /app.jar;\
docker exec -d be-dns-5 java -jar /app.jar;





curl --doh-url https://cloudflare-dns.com/dns-query https://www.google.com



curl --doh-url https://172.0.0.2/dns-query http://be-test.com