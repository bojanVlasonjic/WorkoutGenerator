package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.ConditioningWorkoutDto;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Exercise> exercises;

    public ConditioningWorkout(ConditioningWorkoutDto conditioningWorkoutDto, Workout workout) {

        this.id = conditioningWorkoutDto.getId();
        this.workout = workout;
        this.workInterval = conditioningWorkoutDto.getWorkInterval();
        this.restInterval = conditioningWorkoutDto.getRestInterval();
        this.numberOfIntervals = conditioningWorkoutDto.getNumberOfIntervals();
        this.numberOfRounds = conditioningWorkoutDto.getNumberOfRounds();
        this.restBetweenRounds = conditioningWorkoutDto.getRestBetweenRounds();

        this.exercises = conditioningWorkoutDto
                .getExercises()
                .stream()
                .map(dto -> new Exercise(dto))
                .collect(Collectors.toList());

    }

    // user when generating workout
    public ConditioningWorkout(Workout workout,
                               WorkoutProcessing workoutProcessingDto,
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
