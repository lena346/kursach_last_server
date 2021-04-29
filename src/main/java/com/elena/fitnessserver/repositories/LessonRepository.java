package com.elena.fitnessserver.repositories;

import com.elena.fitnessserver.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByInstructorId(long instructorId);
}
