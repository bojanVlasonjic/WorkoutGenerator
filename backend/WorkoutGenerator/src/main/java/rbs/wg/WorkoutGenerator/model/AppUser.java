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
public class AppUser extends Person {

    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private int repetitionFactor = 1;

    @Column(nullable = false)
    private int numOfExercisesFactor = 1;

    @Column(nullable = false)
    private int workloadFactor = 1;

    @Column(nullable = false)
    private int workIntervalFactor = 0;

    @Enumerated(EnumType.ORDINAL)
    private UserLevel userLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Equipment> equipment;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Workout> workouts;

}
