# Spring boot Distributed tracing with Jaeger

Table of Contents

### Run All in one Jeager (with docker)

``` shell
docker run -d --name jaeger -p 16686:16686 -p 6831:6831/udp jaegertracing/all-in-one:latest
```

**Note:** This all-in-one container will have capablities for in-memory storage onlyy. In productional use case, all the
modules have to be split and loaded for maintenance, upgradation and to avoid single point of failure.

[Refer Architecture](https://www.jaegertracing.io/docs/1.29/architecture/#components)


