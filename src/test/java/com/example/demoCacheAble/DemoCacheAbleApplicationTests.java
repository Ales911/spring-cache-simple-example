package com.example.demoCacheAble;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.springframework.context.ApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoCacheAbleApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(DemoCacheAbleApplicationTests.class);

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Before
    public void before() {
        personRepository.initPersons(List.of(new Person("Ivan", 22),
                new Person("Sergey", 34),
                new Person("Igor", 41)));
    }

    private Person findCacheByName(String name) {
        logger.info("will find: " + name);
        final Person person = personService.findCacheByName(name);
        if (person != null) {
            logger.info("find result: " + person.toString());
            return person;
        } else {
            logger.info("person with name: " + name + " not found");
            return null;
        }
    }
    
    @Test
    public void findByName() {
        assertNotNull(findCacheByName("Ivan"));
        assertNotNull(findCacheByName("Ivan"));
        
        personRepository.add(new Person("Alexander", 69));
        assertNotNull(findCacheByName("Alexander"));
        assertNotNull(findCacheByName("Alexander"));
    }
    
    @Test
    public void addAndFindByName() {
        assertNotNull(personRepository.add(new Person("Alexander", 69)));
        findCacheByName("Alexander");
        personService.addCachable(new Person("Nikolay", 18));
        findCacheByName("Nikolay");
    }
    
    private Person findCacheByAge(Integer age) {
        logger.info("will find: " + age);
        final Person person = personService.findCacheByAge(age);
        logger.info("find result: " + person.toString());
        return person;
    }
    
    @Test
    public void findByAge() {
        assertNotNull(findCacheByAge(22));
        assertNotNull(findCacheByAge(41));
        assertNotNull(findCacheByAge(22));
    }
    
    @Test
    public void findByNameAndDelete () {
        assertNotNull(findCacheByName("Ivan"));
        personService.delete("Ivan");
        assertNull(findCacheByName("Ivan"));
    }

    @Test
    public void findCacheByNameAndPut() {
        findCacheByName("Sergey");
        
        personService.updateAge("Sergey", 99);
        
        Person person = findCacheByName("Sergey");

        assertEquals(Integer.valueOf(99),  person.getAge());
    }

}