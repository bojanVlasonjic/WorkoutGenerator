package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.ExerciseDto;
import rbs.wg.WorkoutGenerator.service.ExerciseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Secured({"ROLE_ADMIN"})
@RequestMapping("api/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExerciseDto> createNewExercise(@RequestBody @Valid ExerciseDto newExercise) {
        return new ResponseEntity<>(
                exerciseService.createNewExercise(newExercise),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<ExerciseDto> updateExercise(@RequestBody @Valid ExerciseDto exercise) {
        return new ResponseEntity<>(
                exerciseService.updateExercise(exercise),
                HttpStatus.OK
        );
    }
}
