package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser extends Person {

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double repetitionFactor = 1;

    @Column(nullable = false)
    private double workLoadFactor = 1;

    @Column(nullable = false)
    private int workIntervalFactor = 0;

    @Column(nullable = false)
    private boolean upperBodyWorked; // whether the user worked upper body or lower body last time

    @Enumerated(EnumType.ORDINAL)
    private UserLevel userLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Equipment> equipment;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Workout> workouts;

}
