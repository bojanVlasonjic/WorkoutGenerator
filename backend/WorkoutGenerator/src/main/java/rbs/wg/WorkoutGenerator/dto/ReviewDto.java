package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.facts.ReviewComplaint;
import rbs.wg.WorkoutGenerator.model.Review;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private Long id;
    private Long workoutId;
    private Long userId;

    private int exertionLevel;
    private int rating;

    private List<ReviewComplaint> complaints;

    public ReviewDto() {
        this.complaints = new ArrayList<>();
    }

    public ReviewDto(Review review){
        this.id = review.getId();
        this.workoutId = review.getWorkout().getId();
        this.userId = review.getUser().getId();

        this.exertionLevel = review.getExertionLevel();
        this.rating = review.getRating();
    }
}
