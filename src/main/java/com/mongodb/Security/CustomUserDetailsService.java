package com.mongodb.Security;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.mongodb.Person.PersonRepository;
import com.mongodb.Person.Person;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WebApplicationContext applicationContext;
    private PersonRepository personRepository;

    public CustomUserDetailsService() {
        super();
    }

    @PostConstruct
    public void completeSetup() {
        personRepository = applicationContext.getBean(PersonRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final Person person = personRepository.findByName(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AppUserPrincipal(person);
    }

}
