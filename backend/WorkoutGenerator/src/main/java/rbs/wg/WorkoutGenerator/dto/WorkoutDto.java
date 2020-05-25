package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.poifs.crypt.temp.SXSSFWorkbookWithCustomZipEntrySource;
import rbs.wg.WorkoutGenerator.model.Workout;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkoutDto {

    private Long id;
    private UUID temporaryId;
    private Long userId;

    private ConditioningWorkoutDto conditioningWorkoutDto;
    private StrengthWorkoutDto strengthWorkoutDto;

    private ReviewDto review;
    private Date date;


    public WorkoutDto(Workout workout) {

        this.id = workout.getId();
        this.userId = workout.getUser().getId();
        this.temporaryId = workout.getTemporaryId();
        this.date = workout.getDate();

        if(workout.getReview() != null) {
            this.review = new ReviewDto(workout.getReview());
        }

        if(workout.getStrengthWorkout() != null) {
            this.strengthWorkoutDto = new StrengthWorkoutDto(workout.getStrengthWorkout());
        }

        if(workout.getConditioningWorkout() != null) {
            this.conditioningWorkoutDto = new ConditioningWorkoutDto(workout.getConditioningWorkout());
        }

    }

}
