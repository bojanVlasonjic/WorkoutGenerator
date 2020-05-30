package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.StrengthWorkoutDto;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "strengthWorkout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StrengthRegime> strengthRegime;

    @OneToOne(mappedBy = "strengthWorkout", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Workout workout;


    public StrengthWorkout(StrengthWorkoutDto strengthWorkoutDto, Workout workout) {
        this.id = strengthWorkoutDto.getId();
        this.restBetweenSets = strengthWorkoutDto.getRestBetweenSets();
        this.workout = workout;

        this.strengthRegime = strengthWorkoutDto.getStrengthRegimes()
                .stream()
                .map(reg -> new StrengthRegime(reg, this))
                .collect(Collectors.toList());
    }

    // user when generating workout
    public StrengthWorkout(Workout workout,
                           WorkoutProcessing workoutProcessing,
                           List<Exercise> exercises) {

        this.restBetweenSets = workoutProcessing.getRestBetweenSets();
        this.workout = workout;

        this.strengthRegime = exercises
                .stream()
                .map(ex -> new StrengthRegime(this, workoutProcessing, ex))
                .collect(Collectors.toList());
    }

}
