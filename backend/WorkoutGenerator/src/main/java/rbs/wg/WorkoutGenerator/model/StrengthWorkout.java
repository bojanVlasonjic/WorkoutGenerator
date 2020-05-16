package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StrengthWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int restBetweenSets;

    @Column(nullable = false)
    private int restBetweenExercises;

    @OneToMany(mappedBy = "strengthWorkout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StrengthRegime> strengthRegime;

    @OneToOne(mappedBy = "strengthWorkout")
    private Workout workout;

}
