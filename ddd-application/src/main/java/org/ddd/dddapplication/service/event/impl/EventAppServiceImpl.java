package org.ddd.dddapplication.service.event.impl;

import org.ddd.dddapplication.service.event.EventAppService;
import org.ddd.ddddomain.service.HiDomainService;
import org.springframework.stereotype.Service;

@Service
public class EventAppServiceImpl implements EventAppService {
    private HiDomainService hiDomainService;

    public EventAppServiceImpl(HiDomainService hiDomainService) {
        this.hiDomainService = hiDomainService;
    }

    @Override
    public String sayHi(String name) {
        return hiDomainService.sayHi(name);
    }
}
