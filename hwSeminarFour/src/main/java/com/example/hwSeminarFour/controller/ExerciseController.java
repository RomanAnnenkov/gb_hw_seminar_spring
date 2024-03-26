package com.example.hwSeminarFour.controller;

import com.example.hwSeminarFour.model.Exercise;
import com.example.hwSeminarFour.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/exercises")
    public String getExercises(Model model) {
        model.addAttribute("exercises", exerciseService.getAllExercises());
        return "exercises.html";
    }

    @PostMapping("/exercises")
    public String addExercise(Exercise exercise, Model model) {
        exerciseService.addExercise(exercise);
        return getExercises(model);
    }
}
