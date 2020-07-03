package rbs.wg.WorkoutGenerator.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.ReviewDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.Review;
import rbs.wg.WorkoutGenerator.model.Workout;
import rbs.wg.WorkoutGenerator.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private AppUserService appUserService;


    public List<ReviewDto> getReviewsForUser(Long userId) {

        return reviewRepository
                .findByUserId(userId)
                .stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }

    public ReviewDto getReviewForWorkout(Long workoutId) {

        return reviewRepository
                .findByWorkoutId(workoutId)
                .map(ReviewDto::new)
                .orElseThrow(() -> new ApiNotFoundException("Review not found for workout"));

    }

    public ReviewDto createWorkoutReview(ReviewDto reviewDto) {

        Workout workout = workoutService
                .findWorkoutById(reviewDto.getWorkoutId())
                .orElseThrow(() -> new ApiNotFoundException("Workout not found"));


        if(workout.getReview() != null) {
            throw new ApiBadRequestException("The workout has already been reviewed");
        }

        AppUser user = appUserService
                .findUserById(reviewDto.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("User not found"));

        if(!workout.getUser().getId().equals(reviewDto.getUserId())) {
            throw new ApiBadRequestException("The given workout does not belong to user");
        }

        adjustIntensityWithReview(reviewDto, user);

        Review review = new Review(reviewDto, workout, user);
        workout.setReview(review);
        review = reviewRepository.save(review);

        return new ReviewDto(review);

    }

    private void adjustIntensityWithReview(ReviewDto reviewDto, AppUser user) {

        // prep session
        KieSession workoutSession = kieContainer.newKieSession("WGSession");

        // insert facts
        workoutSession.insert(user);
        workoutSession.insert(reviewDto);

        // fire rules and destroy session
        workoutSession.getAgenda().getAgendaGroup("review").setFocus();
        workoutSession.fireAllRules();
        workoutSession.dispose();

    }
}
