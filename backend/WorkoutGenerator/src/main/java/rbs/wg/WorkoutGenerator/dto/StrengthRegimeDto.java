package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.StrengthRegime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StrengthRegimeDto {

    private Long id;
    private Long strengthWorkoutId;

    private ExerciseDto exerciseDto;

    private int repetitions;
    private int numberOfSets;
    private int workLoad;

    public StrengthRegimeDto(StrengthRegime strengthRegime) {
        this.id = strengthRegime.getId();
        this.strengthWorkoutId = strengthRegime.getStrengthWorkout().getId();

        this.exerciseDto = new ExerciseDto(strengthRegime.getExercise());

        this.repetitions = strengthRegime.getRepetitions();
        this.numberOfSets = strengthRegime.getNumberOfSets();
        this.workLoad = strengthRegime.getWorkLoad();
    }

}
