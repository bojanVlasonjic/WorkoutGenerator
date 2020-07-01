package rbs.wg.WorkoutGenerator.facts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.ExerciseType;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkoutRequest {

    @NotEmpty(message = "User email is required")
    private String email;

    @NotNull(message = "Specified equipment is required")
    private List<Equipment> specifiedEquipment;

    @NotNull(message = "User identification is required")
    private ExerciseType workoutType;

}
