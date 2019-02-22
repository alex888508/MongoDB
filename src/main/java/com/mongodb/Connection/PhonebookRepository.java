package com.mongodb.Connection;

import com.mongodb.Person.Phonebook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhonebookRepository extends MongoRepository<Phonebook, String> {

        Phonebook findByNumer(String numer);
        Phonebook deleteByNumer(String numer);
    }



