package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/delayed")
    public String delayedEndpoint() throws InterruptedException {
        Thread.sleep(5000); // Introduce a delay of 5 seconds
        throw new RuntimeException("Intentional failure");
    }

    // Calls /delayed that triggers circuit breaker
    @GetMapping("/call-delayed")
    public String callDelayedService() {
        return circuitBreakerFactory.create("delayedServiceCircuitBreaker").run(
                () -> restTemplate.getForObject("http://localhost:8080/delayed", String.class),
                throwable -> getFallbackResponse());
    }

    @GetMapping("/success")
    public String successEndpoint() {
        return "Success";
    }

    // Calls /success with immediate success
    @GetMapping("/call-success")
    public String callSuccessService() {
        return circuitBreakerFactory.create("successServiceCircuitBreaker").run(
                () -> restTemplate.getForObject("http://localhost:8080/success", String.class),
                throwable -> getFallbackResponse());
    }

    private String getFallbackResponse() {
        return "Fallback response due to timeout or failure";
    }
}
