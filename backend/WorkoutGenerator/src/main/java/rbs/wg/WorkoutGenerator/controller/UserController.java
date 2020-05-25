package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
import rbs.wg.WorkoutGenerator.service.WorkoutRequestService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private WorkoutRequestService workoutRequestService;

    @PostMapping("/process-workout")
    public ResponseEntity<WorkoutDto> processWorkout(@Valid @RequestBody WorkoutRequestDto workoutRequest) {

        return new ResponseEntity<>(
                this.workoutRequestService.processWorkout(workoutRequest),
                HttpStatus.ACCEPTED);
    }



}
