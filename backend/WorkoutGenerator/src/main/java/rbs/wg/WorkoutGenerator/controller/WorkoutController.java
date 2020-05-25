package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<WorkoutDto>> getUserWorkouts(@PathVariable Long userId) {
        return new ResponseEntity<>(workoutService.getUserWorkouts(userId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        return new ResponseEntity<>(workoutService.createUserWorkout(workoutDto), HttpStatus.CREATED);
    }

}
