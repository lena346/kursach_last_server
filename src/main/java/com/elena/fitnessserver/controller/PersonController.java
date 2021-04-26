//package com.elena.fitnessserver.controller;
//
//import com.elena.fitnessserver.exceptions.ResourceNotFoundException;
//import com.elena.fitnessserver.models.Person;
//import com.elena.fitnessserver.repositories.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class PersonController {
//
//    @Autowired
//    private PersonRepository personRepository;
//
//    @PostMapping(value = "/persons")
//    public Person create(@RequestBody Person person) {
//        return personRepository.save(person);
//    }
//
//    @GetMapping(value = "/persons")
//    public ResponseEntity<List<Person>> read() {
//        List<Person> people = personRepository.findAll();
//        return !people.isEmpty()
//                ? new ResponseEntity<>(people, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping(value = "/persons/{id}")
//    public ResponseEntity<Person> read(@PathVariable(name = "id") Long id) {
//        Person person = personRepository.findById(id).orElse(null);
//
//        return person != null
//                ? new ResponseEntity<>(person, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping(value = "/persons/{id}")
//    public Person update(@PathVariable(name = "id") Long personId, @RequestBody Person personReq) {
//        return personRepository.findById(personId).map(
//                person -> {
//                    person = personReq;
//                    return personRepository.save(person);
//                }
//        ).orElseThrow(() -> new ResourceNotFoundException("Person not found with id" + personId));
//    }
//
//    @DeleteMapping(value = "/persons/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") Long personId) {
//        return personRepository.findById(personId)
//                .map(person -> {
//                    personRepository.delete(person);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Person not found with id" + personId));
//    }
//
//}
