FROM openjdk:8-jre-alpine

COPY ./target/jaeger-tracing.jar /tmp

WORKDIR /tmp
CMD java -jar jaeger-tracing.jar
EXPOSE 9000

