package rbs.wg.WorkoutGenerator.facts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.PropertyReactive;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

@Getter
@Setter
public class WorkoutProcessing {

    private int[] nextMuscleGroups;
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

    public WorkoutProcessing() {
        this.nextMuscleGroups = new int[2];
    }

    public void setMuscleGroup(int index, int muscleGroup) {
        this.nextMuscleGroups[index] = muscleGroup;
    }
}
