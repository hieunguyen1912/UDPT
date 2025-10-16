package org.ddd.dddinfrastructure.persistence.repository;

import org.ddd.ddddomain.repository.HiDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HiInfraRepositoryImpl implements HiDomainRepository {
    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }
}
