package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.StrengthRegimeDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StrengthRegime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StrengthWorkout strengthWorkout;

    @Column(nullable = false)
    private int repetitions;

    @Column(nullable = false)
    private int numberOfSets;

    @Column
    private int workLoad;

    public StrengthRegime(StrengthRegimeDto strengthRegimeDto, StrengthWorkout strengthWorkout) {

        this.strengthWorkout = strengthWorkout;
        this.exercise = new Exercise(strengthRegimeDto.getExerciseDto());
        this.repetitions = strengthRegimeDto.getRepetitions();
        this.numberOfSets = strengthRegimeDto.getNumberOfSets();
        this.workLoad = strengthRegimeDto.getWorkLoad();

    }

    public StrengthRegime(StrengthWorkout strengthWorkout,
                          WorkoutProcessingDto workoutProcessing,
                          Exercise exercise) {
        this.exercise = exercise;
        this.strengthWorkout = strengthWorkout;

        this.repetitions = workoutProcessing.getReps();
        this.numberOfSets = workoutProcessing.getSets();
    }
}
