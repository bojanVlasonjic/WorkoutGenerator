package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.ConditioningWorkout;
import rbs.wg.WorkoutGenerator.model.Exercise;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConditioningWorkoutDto {

    private Long id;
    private Long workoutId;

    private int workInterval;
    private int restInterval;
    private int numberOfIntervals;

    private int numberOfRounds;
    private int restBetweenRounds;

    private List<ExerciseDto> exercises;

    public ConditioningWorkoutDto(ConditioningWorkout conditioningWorkout) {

        this.id = conditioningWorkout.getId();
        this.workoutId = conditioningWorkout.getWorkout().getId();

        this.workInterval = conditioningWorkout.getWorkInterval();
        this.restInterval = conditioningWorkout.getRestInterval();
        this.numberOfIntervals = conditioningWorkout.getNumberOfIntervals();

        this.numberOfRounds = conditioningWorkout.getNumberOfRounds();
        this.restBetweenRounds = conditioningWorkout.getRestBetweenRounds();

        this.exercises =
                conditioningWorkout
                .getExercises()
                .stream()
                .map(ex -> new ExerciseDto(ex))
                .collect(Collectors.toList());


    }

}
