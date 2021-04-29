package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.models.Instructor;
import com.elena.fitnessserver.models.Lesson;

public class AddLesson {
    Instructor instructor;
    Lesson lesson;

    public AddLesson(Instructor instructor, Lesson lesson) {
        this.instructor = instructor;
        this.lesson = lesson;
    }
}
