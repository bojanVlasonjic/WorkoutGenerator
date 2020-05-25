package rbs.wg.WorkoutGenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.rule.All;
import rbs.wg.WorkoutGenerator.dto.ExerciseDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@All
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Equipment equipment;

    @Enumerated(EnumType.ORDINAL)
    private ExerciseType exerciseType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<MuscleGroup> targetedMuscles;

    public Exercise(ExerciseDto exerciseDto) {
        updateExercise(exerciseDto);
    }

    public void updateExercise(ExerciseDto exerciseDto) {
        this.name = exerciseDto.getName();
        this.description = exerciseDto.getDescription();
        this.equipment = exerciseDto.getEquipment();
        this.exerciseType = exerciseDto.getExerciseType();
        this.targetedMuscles = exerciseDto.getTargetedMuscles();
    }
}
