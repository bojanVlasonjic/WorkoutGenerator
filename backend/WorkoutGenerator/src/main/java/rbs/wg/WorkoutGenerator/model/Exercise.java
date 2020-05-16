package rbs.wg.WorkoutGenerator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.rule.All;

import javax.persistence.*;
import java.util.Set;

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

    @Column(nullable = false)
    private Equipment equipment;

    @Column(nullable = false)
    private ExerciseType exerciseType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<MuscleGroup> targetedMuscles;
}
