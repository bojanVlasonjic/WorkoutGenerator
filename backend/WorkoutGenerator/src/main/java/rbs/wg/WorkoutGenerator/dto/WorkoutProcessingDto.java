package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Exercise;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WorkoutProcessingDto {

    private MuscleGroup nextMuscleGroup;
    private int numOfExercises;

    // strength workout data
    private int reps;
    private int sets;
    private int restBetweenSets;

    // conditioning workout data
    private int workInterval;
    private int restInterval;
    private int numOfIntervals;
    private int numOfRounds;
    private int restBetweenRounds;

    private List<Exercise> selectedExercises;
}
