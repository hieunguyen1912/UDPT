package org.ddd.dddcontroller.http;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.ddd.dddapplication.service.event.EventAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

    private EventAppService eventAppService;
    private RestTemplate restTemplate;

    public HiController(EventAppService eventAppService, RestTemplate restTemplate) {
        this.eventAppService = eventAppService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    @RateLimiter(name = "backendA", fallbackMethod = "fallbackHello")
    public String hello() {
        return eventAppService.sayHi("Hieu");
    }

    public String fallbackHello(Throwable throwable) {
        return "Too many request";
    }

    @GetMapping("/circuit/breaker")
    @CircuitBreaker(name = "backendB", fallbackMethod = "fallbackCircuitBreaker")
    public String circuitBreaker() {
        String url = "https://fakestoreapi.com/products/7";
        return restTemplate.getForObject(url, String.class);
    }

    public String fallbackCircuitBreaker(Throwable throwable) {
        return "Service CircuitBreaker error";
    }
}
