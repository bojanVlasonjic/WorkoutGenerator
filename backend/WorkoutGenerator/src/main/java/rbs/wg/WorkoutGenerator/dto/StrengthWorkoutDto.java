package rbs.wg.WorkoutGenerator.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.StrengthWorkout;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StrengthWorkoutDto {

    private Long id;
    private Long workoutId;

    private int restBetweenSets;

    private List<StrengthRegimeDto> strengthRegimes;

    public StrengthWorkoutDto(StrengthWorkout strengthWorkout) {
        this.id = strengthWorkout.getId();
        this.workoutId = strengthWorkout.getWorkout().getId();

        this.restBetweenSets = strengthWorkout.getRestBetweenSets();

        this.strengthRegimes =
                strengthWorkout
                .getStrengthRegime()
                .stream()
                .map(sr -> new StrengthRegimeDto(sr))
                .collect(Collectors.toList());
    }

}
