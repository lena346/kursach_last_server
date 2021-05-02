package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.exceptions.ResourceNotFoundException;
import com.elena.fitnessserver.models.Lesson;
import com.elena.fitnessserver.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping(value = "/lessons")
    public Lesson create(@RequestBody AddLesson newLesson) {
        Lesson lesson = newLesson.lesson;
        lesson.setInstructor(newLesson.instructor);
        return lessonRepository.save(lesson);
    }

    @GetMapping(value = "/lessons/{instructorId}")
    public ResponseEntity<List<Lesson>> readByInstructorId(@PathVariable long instructorId) {
        System.out.println(instructorId);
        return new ResponseEntity<>(lessonRepository.findAllByInstructorId(instructorId), HttpStatus.OK);
    }

    @GetMapping(value = "/lessons")
    public ResponseEntity<List<Lesson>> read() {
        List<Lesson> lesson = lessonRepository.findAll();
        return !lesson.isEmpty()
                ? new ResponseEntity<>(lesson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/lessons/{id}")
    public Lesson update(@PathVariable(name = "id") Long lessonId, @RequestBody Lesson lessonReq) {
        return lessonRepository.findById(lessonId).map(
                lesson -> {
                    lesson = lessonReq;
                    return lessonRepository.save(lesson);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id" + lessonId));
    }

    @DeleteMapping(value = "/lessons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lesson -> {
                    lessonRepository.delete(lesson);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id" + lessonId));
    }

}
