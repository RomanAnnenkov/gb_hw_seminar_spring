package com.example.hwSeminarFour.service;

import com.example.hwSeminarFour.model.Exercise;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExerciseService {
    private final List<Exercise> exercises = new ArrayList<>();

    public List<Exercise> getAllExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

}
