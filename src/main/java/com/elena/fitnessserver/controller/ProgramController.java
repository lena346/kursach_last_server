package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.exceptions.ResourceNotFoundException;
import com.elena.fitnessserver.models.Program;
import com.elena.fitnessserver.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @PostMapping(value = "/programs")
    public Program create(@RequestBody Program program) {
        return programRepository.save(program);
    }

    @GetMapping(value = "/programs")
    public ResponseEntity<List<Program>> read() {
        List<Program> people = programRepository.findAll();
        return !people.isEmpty()
                ? new ResponseEntity<>(people, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/programs/{id}")
    public ResponseEntity<Program> read(@PathVariable(name = "id") Long id) {
        Program program = programRepository.findById(id).orElse(null);

        return program != null
                ? new ResponseEntity<>(program, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/programs/{id}")
    public Program update(@PathVariable(name = "id") Long programId, @RequestBody Program programReq) {
        return programRepository.findById(programId).map(
                program -> {
                    program = programReq;
                    return programRepository.save(program);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Program not found with id" + programId));
    }

    @DeleteMapping(value = "/programs/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long programId) {
        return programRepository.findById(programId)
                .map(program -> {
                    programRepository.delete(program);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Program not found with id" + programId));
    }

}
