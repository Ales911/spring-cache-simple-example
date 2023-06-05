package com.example.demoCacheAble;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Cacheable(cacheNames = "persons")
    public Person findCacheByName(String name) {
        logger.info("finding person: " + name);
        return personRepository.findByName(name);
    }

    @Cacheable(value = "persons_age", key = "#age")
    public Person findCacheByAge(Integer age) {
        logger.info("finding age: " + age);
        return personRepository.findByAge(age);
    }

    @CachePut(cacheNames = "persons")
    public Person findByNameAndPut(String name) {
        logger.info("findingByName and puting person ... " + name);
        final Person person = personRepository.findByName(name);
        logger.info("put in cache person " + person);
        return person;
    }
    
    @CachePut(cacheNames = "persons", key = "#name")
    public Person updateAge(String name, Integer age) {
        logger.info("update person: " + name + ":" + age);
        final Person person = personRepository.findByName(name);
        person.setAge(age);
        return person;
    }

    @CacheEvict("persons")
    public boolean delete(String name) {
        final Person person = personRepository.findByName(name);
        return personRepository.remove(person);
    }

    @Cacheable(cacheNames = "persons", key = "#person.name")
    public Person addCachable(Person person) {
        personRepository.add(person);
        return person;
    }

    @Caching(
            cacheable = {
                    @Cacheable("person"),
                    @Cacheable("contacts")
            },
            put = {
                    @CachePut("tables"),
                    @CachePut("chairs"),
                    @CachePut(value = "emals", key = "#person.email")
            },
            evict = {
                    @CacheEvict(value = "services", key = "#person.name")
            }
    )
    public Person complicatedUpdate() {
        return null;
    }
}