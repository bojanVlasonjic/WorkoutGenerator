package rbs.wg.WorkoutGenerator.facts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.PropertyReactive;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.UserLevel;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
@PropertyReactive
public class UserInformation {

    private boolean upperBodyWorked;
    private UserLevel userLevel;
    private double weight;

    private double repetitionFactor;
    private double numOfExercisesFactor;
    private double workloadFactor;
    private int workIntervalFactor;

    public UserInformation(AppUser user) {
        this.upperBodyWorked = user.isUpperBodyWorked();
        this.userLevel = user.getUserLevel();
        this.weight = user.getWeight();

        this.repetitionFactor = user.getRepetitionFactor();
        this.numOfExercisesFactor = user.getNumOfExercisesFactor();
        this.workloadFactor = user.getWorkloadFactor();
        this.workIntervalFactor = user.getWorkIntervalFactor();
    }
}
