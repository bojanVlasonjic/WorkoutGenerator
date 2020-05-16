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
public class ConditioningWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int workInterval;

    @Column(nullable = false)
    private int restInterval;

    @Column(nullable = false)
    private int numberOfIntervals;

    @Column(nullable = false)
    private int numberOfRounds;

    @Column(nullable = false)
    private int restBetweenRounds;

    @OneToOne(mappedBy = "conditioningWorkout")
    private Workout workout;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Exercise> exercises;

}
