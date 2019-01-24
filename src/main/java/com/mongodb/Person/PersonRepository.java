package com.mongodb.Person;

import com.mongodb.Person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PersonRepository extends MongoRepository<Person, String> {
    Person findByName(String name);
    public void updateLastLogin(@Param("lastLogin") Date lastLogin);
}
