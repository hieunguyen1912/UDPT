package org.ddd.dddcontroller;

import org.ddd.dddapplication.service.event.EventAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    private EventAppService eventAppService;

    public HiController(EventAppService eventAppService) {
        this.eventAppService = eventAppService;
    }

    @GetMapping("/")
    public String hello() {
        return eventAppService.sayHi("Hieu");
    }
}
