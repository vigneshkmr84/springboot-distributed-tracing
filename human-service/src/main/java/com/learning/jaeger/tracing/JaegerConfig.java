package com.learning.jaeger.tracing;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class JaegerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public JaegerTracer jaegerTracer() {

        log.info("Creating jaeger configurations bean");
        return new io.jaegertracing.Configuration(appName)
                .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE)
                        .withParam(1))
                .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true))
                .getTracer();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
