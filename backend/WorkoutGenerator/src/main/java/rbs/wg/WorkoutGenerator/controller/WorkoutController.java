package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.facts.WorkoutRequest;
import rbs.wg.WorkoutGenerator.service.WorkoutRequestService;
import rbs.wg.WorkoutGenerator.service.WorkoutService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutRequestService workoutRequestService;


    @GetMapping("/{email}")
    public ResponseEntity<List<WorkoutDto>> getUserWorkouts(@PathVariable String email) {
        return new ResponseEntity<>(workoutService.getUserWorkouts(email), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        return new ResponseEntity<>(workoutService.createUserWorkout(workoutDto), HttpStatus.CREATED);
    }

    @PostMapping("/generate")
    public ResponseEntity<WorkoutDto> generateWorkout(@Valid @RequestBody WorkoutRequest workoutRequest) {
        return new ResponseEntity<>(workoutRequestService.generateWorkout(workoutRequest), HttpStatus.ACCEPTED);
    }

}
