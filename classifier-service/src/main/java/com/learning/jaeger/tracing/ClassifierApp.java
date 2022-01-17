package com.learning.jaeger.tracing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Random;

@SpringBootApplication
@RestController
@Slf4j
public class ClassifierApp {

    @Value("${daddy.service.api}")
    String daddyJokesAPI;
    @Value("${animal.service.api}")
    String animalJokesAPI;
    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ClassifierApp.class, args);
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        log.info("Health OK");
        return ResponseEntity.ok().body("OK");
    }


    @GetMapping("/hello/{name}")
    public ResponseEntity<?> hello(@PathVariable("name") String name) throws InterruptedException {

        sleep();
        log.info("name : " + name);
        return ResponseEntity.ok().body("Hello " + name + " !");
    }

    @GetMapping("/joke/{category}")
    public ResponseEntity<?> joke(@PathVariable("category") String category) {


        String finalAPI = "";
        if (category.equalsIgnoreCase("animal")) {
            finalAPI = animalJokesAPI;
        } else if (category.equalsIgnoreCase("daddy"))
            finalAPI = daddyJokesAPI;
        else {
            log.error("Invalid Category : " + category);
            return ResponseEntity.badRequest().body("Invalid Category : " + category);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<?> entity = RequestEntity.get(URI.create(finalAPI))
                .headers(headers)
                .build();

        ResponseEntity<String> responseJson = restTemplate.exchange(entity, String.class);

        String responseString = responseJson.getBody();
        log.info("Response Code : " + responseJson.getStatusCodeValue());
        log.info("JSON Response " + responseString);

        return ResponseEntity.ok().body(responseString);
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(new Random().nextInt((350 - 150) + 1) + 150);
    }
}
