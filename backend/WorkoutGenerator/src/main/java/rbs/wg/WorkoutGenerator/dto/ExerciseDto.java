package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.Exercise;
import rbs.wg.WorkoutGenerator.model.ExerciseType;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ExerciseDto {

    private Long id;

    @NotBlank(message = "Exercise name is required")
    private String name;

    @NotBlank(message = "Exercise description is required")
    private String description;

    @NotNull(message = "Exercise equipment is required")
    private Equipment equipment;

    @NotNull(message = "Exercise type is required")
    private ExerciseType exerciseType;

    @NotNull(message = "Targeted muscles are required")
    private Set<MuscleGroup> targetedMuscles;

    public ExerciseDto(Exercise exercise) {
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.description = exercise.getDescription();
        this.equipment = exercise.getEquipment();
        this.exerciseType = exercise.getExerciseType();

        this.targetedMuscles = exercise.getTargetedMuscles();
    }
}
