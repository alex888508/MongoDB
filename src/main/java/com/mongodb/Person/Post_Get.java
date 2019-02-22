package com.mongodb.Person;

import com.mongodb.Connection.PersonRepository;
import com.mongodb.Connection.PhonebookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;


@RestController
public class Post_Get {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhonebookRepository phoneRepository;
    private static Logger logger = LoggerFactory.getLogger(Post_Get.class);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    @PreAuthorize("hasPermission(#user, 'write')")
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User create(@RequestBody final User user) {
        return user;

    }
    @GetMapping(value = "user/info{token_e}")
    public ResponseEntity<Token> post(@PathVariable String token_e) {
        Token token = new Token();
        String role = "editor";
        logger.debug("Success GetInfo");
        token.setData(token.getRoles());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(value = "user/base{numer}")
    public ResponseEntity<?> SearchByNumer(@PathVariable String numer) {
        Phonebook phone = phoneRepository.findByNumer(numer);
        if (phone == null) {
            logger.info("Not found numer: " + numer);
            return new ResponseEntity<java.io.Serializable>(("Person with id " + numer + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @GetMapping(value = "user/base")
    public List<Phonebook> SearchAll (){
        logger.debug("Get users from Phonebook");
        return this.phoneRepository.findAll();
    }


    @PostMapping("user/add")
    public ResponseEntity<?> createPhoneUser(@RequestBody Phonebook newPhone) {
        logger.debug("POSTing person" + newPhone );
        return new ResponseEntity<>(phoneRepository.save(newPhone), HttpStatus.OK);
    }

    @RequestMapping(value = "user/delete/{numer}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserByPhone (@PathVariable String numer) {
        Phonebook phone = phoneRepository.findByNumer(numer);
        if (phone == null) {
            logger.debug("User with numer " + numer + " not found");
            return new ResponseEntity<java.io.Serializable>(("Unable to delete. User with numer " + numer + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        phoneRepository.deleteByNumer(numer);
        logger.debug("User with numer " + numer + " deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "user/edit/{numer}")
    public ResponseEntity<?> editPerson(@PathVariable String numer, @RequestBody Phonebook user) throws ChangeSetPersister.NotFoundException {
        Phonebook new_user = phoneRepository.findByNumer(numer);
        logger.debug("numer - " + numer);
        if (new_user == null) {
            return new ResponseEntity<java.io.Serializable>(("Unable to delete. User with numer " + numer + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        new_user.setDate(user.getDate());
        new_user.setInfo(user.getInfo());
        new_user.setName(user.getName());
        new_user.setNumer(user.getNumer());
        logger.debug("Puting user - " + user.getName() + ", " + new_user.getName());
        return new ResponseEntity<>(phoneRepository.save(new_user), HttpStatus.OK);
    }

    @PostMapping("api/names/users")
    public ResponseEntity<?> createPerson(@RequestParam String username, String password) {
        logger.info("Success_Post");
        Person newPerson = new Person(username, passwordEncoder.encode(password));
        logger.debug("POSTing person" + newPerson );
        return new ResponseEntity<>(personRepository.save(newPerson), HttpStatus.OK);
    }

    @GetMapping("api/names")
    public List<Person> getNames() {
        logger.info("Success_Get");
        return personRepository.findAll();
    }

    @PutMapping(value = "api/names/{username}")
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable String username) throws ChangeSetPersister.NotFoundException {
        Person currentPerson = personRepository.findById(username).orElseThrow(()->new ChangeSetPersister.NotFoundException());
        currentPerson.setEmail(person.getEmail());
        currentPerson.setPassword(person.getPassword());
        currentPerson.setOld(person.getOld());
        return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.OK);
    }

    @RequestMapping(value = "api/names", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson (@RequestParam("username") String username) {
        Person currentPerson = personRepository.findByName(username);
        if (currentPerson == null) {
            return new ResponseEntity<java.io.Serializable>(("Unable to delete. Person with id " + username + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        personRepository.deleteByName(username);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
}
