package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.ReviewDto;
import rbs.wg.WorkoutGenerator.service.ReviewService;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {

        return new ResponseEntity<>(
                reviewService.createWorkoutReview(reviewDto),
                HttpStatus.OK
        );
    }

}
