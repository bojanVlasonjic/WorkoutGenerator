package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
import rbs.wg.WorkoutGenerator.service.WorkoutRequestService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    WorkoutRequestService workoutRequestService;

    @PostMapping("/process-workout")
    public ResponseEntity<WorkoutProcessingDto> processWorkout(@RequestBody WorkoutRequestDto workoutRequest) {

        return new ResponseEntity<>(
                this.workoutRequestService.processWorkout(workoutRequest),
                HttpStatus.ACCEPTED);
    }



}
