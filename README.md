# Spring sample for DNS load balance integration

This project was created to test how spring would manage the http connections to a backend that runs multiple instances that are balanced trough a DNS load balance
This is the project test diagram;
![Test diagram](diagram/dns-load-balance.png)

## Running the project
you can run this project using docker compose or running one by one the containers.

### To run using docker compose;
run;
```sh
docker-compose up -d
```
stop;
```sh
docker-compose stop
```

### To run one by one

Follow the instructions on each folder;
* [DNS (Bind9)](/bind9)
* [Interaction API](/spring-sample/interaction-api)
* [Backend](/spring-sample/be)

### Useful scripts

Curl BE from interaction-api;
```sh
curl --location --request POST 'http://127.0.0.1:9090/sample' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "waitForMilliseconds": 1000
}'
```

Build and run `interaction-api` from the interaction project folder
```sh
docker build -t interaction-api .;\
docker run -d --name=interaction-api --net=dns-load-balance-net --ip=172.0.0.254 --dns=172.0.0.2 -p 8080:8080 interaction-api
```

Stop and remove all `interacgtion-api` dependencies
```sh
docker stop interaction-api;\
docker rm interaction-api;\
docker rmi interaction-api
```

Build and run `be` from the back end project folder
```sh
docker build -t be-dns .;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.3 --dns=172.0.0.2 -p 0.0.0.0:9090:9090 --name=be-dns-3 be-dns;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.4 --dns=172.0.0.2 -p 0.0.0.0:9094:9090 --name=be-dns-4 be-dns;\
docker run -d --net=dns-load-balance-net --ip=172.0.0.5 --dns=172.0.0.2 -p 0.0.0.0:9095:9090 --name=be-dns-5 be-dns;\
docker exec -d be-dns-3 java -jar /app.jar;\
docker exec -d be-dns-4 java -jar /app.jar;\
docker exec -d be-dns-5 java -jar /app.jar;
```

Stop and remove all `be` dependencies
```sh
docker stop be-dns-3;\
docker stop be-dns-4;\
docker stop be-dns-5;\
docker rm be-dns-3;\
docker rm be-dns-4;\
docker rm be-dns-5;\
docker rmi be-dns
```
