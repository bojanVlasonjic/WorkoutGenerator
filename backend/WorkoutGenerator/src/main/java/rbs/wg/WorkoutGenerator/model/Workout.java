package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID temporaryId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ConditioningWorkout conditioningWorkout;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StrengthWorkout strengthWorkout;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Review review;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    public Workout(WorkoutDto workoutDto, AppUser appUser) {

        this.date = workoutDto.getDate();
        this.user = appUser;

        if(workoutDto.getConditioningWorkoutDto() != null) {
            this.conditioningWorkout = new ConditioningWorkout(workoutDto.getConditioningWorkoutDto(), this);
        }

        if(workoutDto.getStrengthWorkoutDto() != null) {
            this.strengthWorkout = new StrengthWorkout(workoutDto.getStrengthWorkoutDto(), this);
        }

        if(workoutDto.getReview() != null) {
            this.review = new Review(workoutDto.getReview(), this, appUser);
        }

    }

    public void initWorkout(StrengthWorkout strengthWorkout,
                            ConditioningWorkout conditioningWorkout,
                            AppUser user, Date date) {

        this.user = user;
        this.date = date;

        if(strengthWorkout != null) { this.strengthWorkout = strengthWorkout; }
        if(conditioningWorkout != null) { this.conditioningWorkout = conditioningWorkout; }
    }
}
