package com.example.demoCacheAble;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonRepository {

    private final List<Person> persons = new ArrayList<>();

    public void initPersons(List<Person> persons) {
        this.persons.addAll(persons);
    }

    public Person add(Person person) {
        persons.add(person);
        return person;
    }

    public boolean remove(Person person) {
        return persons.remove(person);
    }

    public Person findByName(String name) {
        return persons.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Person findByAge(Integer age) {
        return persons.stream()
                .filter(p -> p.getAge().equals(age))
                .findFirst()
                .orElse(null);
    }

}
