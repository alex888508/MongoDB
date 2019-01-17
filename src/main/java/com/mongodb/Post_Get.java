package com.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class Post_Get {
    @Autowired
    private PersonRepository personRepository;
    private static Logger logger = LoggerFactory.getLogger(Post_Get.class);

    @PostMapping("api/names/users")
    public ResponseEntity<?> createPerson(@RequestBody Person newPerson) {
        logger.info("Success_Post");
        return new ResponseEntity<Person>(personRepository.save(newPerson), HttpStatus.OK);
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
        return new ResponseEntity<Person>(personRepository.save(currentPerson), HttpStatus.OK);
    }

    @RequestMapping(value = "api/names", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson (@RequestParam("username") String username) {
        Person currentPerson = personRepository.findByName(username);
        if (currentPerson == null) {
            return new ResponseEntity (("Unable to delete. Person with id " + username + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        personRepository.deleteById(username);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
}
