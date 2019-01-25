package com.mongodb.controller;

import com.mongodb.Person.Person;
import com.mongodb.Person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/persons")
@RestController
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonRepository personRepository;

    @Autowired
    PersonController(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> allPersons() {

        logger.debug("Request all persons");
        return personRepository.findAll();
    }
}
