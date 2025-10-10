package org.ddd.dddapplication.service.event.impl;

import org.ddd.dddapplication.service.event.EventAppService;
import org.springframework.stereotype.Service;

@Service
public class EventAppServiceImpl implements EventAppService {
    @Override
    public String sayHi(String name) {
        return "Hello " + name;
    }
}
