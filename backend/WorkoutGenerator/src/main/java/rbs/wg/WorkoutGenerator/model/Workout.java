package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID temporaryId;

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

    public void initWorkout(StrengthWorkout strengthWorkout,
                            ConditioningWorkout conditioningWorkout,
                            AppUser user, Date date) {

        this.user = user;
        this.date = date;

        if(strengthWorkout != null) { this.strengthWorkout = strengthWorkout; }
        if(conditioningWorkout != null) { this.conditioningWorkout = conditioningWorkout; }
    }
}
