package rbs.wg.WorkoutGenerator.facts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.ExerciseType;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkoutRequest {

    @NotNull(message = "User identification is required")
    private Long userId;

    @NotNull(message = "Specified equipment is required")
    private List<Equipment> specifiedEquipment;

    @NotNull(message = "User identification is required")
    private ExerciseType workoutType;

    private MuscleGroup targetedMuscle;
}
