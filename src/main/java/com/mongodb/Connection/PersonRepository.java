package com.mongodb.Connection;

import com.mongodb.Person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByName(String name);
    Person deleteByName(String name);
}


