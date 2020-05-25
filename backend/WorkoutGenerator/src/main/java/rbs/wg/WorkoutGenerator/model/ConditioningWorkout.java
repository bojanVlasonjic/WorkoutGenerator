package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConditioningWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int workInterval;

    @Column(nullable = false)
    private int restInterval;

    @Column(nullable = false)
    private int numberOfIntervals;

    @Column(nullable = false)
    private int numberOfRounds;

    @Column(nullable = false)
    private int restBetweenRounds;

    @OneToOne(mappedBy = "conditioningWorkout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Workout workout;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Exercise> exercises;

    public ConditioningWorkout(Workout workout,
                               WorkoutProcessingDto workoutProcessingDto,
                               List<Exercise> exercises) {

        this.workout = workout;
        this.workInterval = workoutProcessingDto.getWorkInterval();
        this.restInterval = workoutProcessingDto.getRestInterval();
        this.numberOfIntervals = workoutProcessingDto.getNumOfIntervals();
        this.numberOfRounds = workoutProcessingDto.getNumOfRounds();
        this.restBetweenRounds = workoutProcessingDto.getRestBetweenRounds();
        this.exercises = exercises;

    }

}
