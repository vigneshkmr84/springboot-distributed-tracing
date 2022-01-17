# Spring boot Distributed tracing with Jaeger

Table of Contents




### Run All in one Jeager (with docker)
``` shell
docker run -d --name jaeger -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411 jaegertracing/all-in-one:latest

#use this for normal 
 # -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411

```

**Note:** This all-in-one container will have capablities for in-memory storage onlyy. In productional use case, all the modules have to be split and loaded for maintenance, upgradation and to avoid single point of failure. 

[Pls refer this article to Setup Elastic Search Cluster with docker-compose](https://www.elastic.co/guide/en/elastic-stack-get-started/current/get-started-docker.html?baymax=rec&rogue=rec-1&elektra=guide)

[Single Node Elastic Search setup](https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html?baymax=rec&rogue=rec-1&elektra=guide#docker-cli-run-dev-mode)

Reference: [ Jaeger Architecture](https://www.jaegertracing.io/docs/1.29/architecture/#components)


Collector Reference: 
- To view the List of available Kafka Configurations, use this Command:
   `docker run -e SPAN_STORAGE_TYPE=kafka jaegertracing/jaeger-collector:latest --help `


