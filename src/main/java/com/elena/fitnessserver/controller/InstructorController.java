package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.exceptions.ResourceNotFoundException;
import com.elena.fitnessserver.models.Instructor;
import com.elena.fitnessserver.models.Program;
import com.elena.fitnessserver.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    @Autowired //###############
    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @PostMapping(value = "/instructors")
    public Instructor create(@RequestBody Instructor instructor) {
//        System.out.println(instructor);
        return instructorRepository.save(instructor);
    }


    @GetMapping(value = "/instructors")
    public ResponseEntity<List<Instructor>> read() {
        List<Instructor> people = instructorRepository.findAll();
        return !people.isEmpty()
                ? new ResponseEntity<>(people, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/instructors/{id}")
    public ResponseEntity<Instructor> read(@PathVariable(name = "id") Long id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);

        return instructor != null
                ? new ResponseEntity<>(instructor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "instructors/{id}")
    public Instructor update(@PathVariable(name = "id") Long instructorId, @RequestBody Instructor instructorReq) {
        return instructorRepository.findById(instructorId).map(
                instructor -> {
                    instructor = instructorReq;
                    return instructorRepository.save(instructor);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id" + instructorId));
    }

    @DeleteMapping(value = "/instructors/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long instructorId) {
        return instructorRepository.findById(instructorId)
                .map(instructor -> {
                    instructorRepository.delete(instructor);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id" + instructorId));
    }

}
