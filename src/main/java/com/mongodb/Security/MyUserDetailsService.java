package com.mongodb.Security;

import com.mongodb.Person.Person;
import com.mongodb.Connection.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
    private PersonRepository personRepository;

    @Autowired
    MyUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {

        log.debug("Find person by username " + username);
        final Person person = personRepository.findByName(username);

        if (person == null) {

            log.warn("Person by username " + username + " not found");
            throw new UsernameNotFoundException(username);
        }

        log.debug("Person by username " + username + " found with data " + person);
        return new User(username, person.getPassword(), new ArrayList<>());
    }
}
