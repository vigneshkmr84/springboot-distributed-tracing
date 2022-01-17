package com.learning.jaeger.tracing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Random;

@RestController
@Slf4j
public class Controller {

    public static final String JOKE_API = "https://icanhazdadjoke.com";

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/health")
    public ResponseEntity<?> health() {
        log.info("Health OK");
        return ResponseEntity.ok().body("OK");
    }


    @GetMapping("/joke")
    public ResponseEntity<?> joke() throws InterruptedException {

        sleep();

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<?> entity = RequestEntity.get(URI.create(JOKE_API))
                .headers(headers)
                .build();

        ResponseEntity<String> responseJson = restTemplate.exchange(entity, String.class);

        String responseString = responseJson.getBody();
        log.info("JSON Response : " + responseString);

        return ResponseEntity.ok().body(responseString);
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(new Random().nextInt((350 - 150) + 1) + 150);
    }
}


