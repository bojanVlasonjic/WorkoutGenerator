package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.ExerciseType;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkoutRequestDto {

    private Long userId;
    private List<Equipment> specifiedEquipment;
    private ExerciseType workoutType;
    private boolean autoGenerate;
}
