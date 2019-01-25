package com.mongodb.Security;


import com.mongodb.Person.Person;
import com.mongodb.Person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    public MyUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final Person person = personRepository.findByName(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUser(person);
    }
}
