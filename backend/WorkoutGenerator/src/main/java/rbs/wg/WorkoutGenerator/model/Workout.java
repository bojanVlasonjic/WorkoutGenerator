package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ConditioningWorkout conditioningWorkout;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StrengthWorkout strengthWorkout;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Review review;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
}
