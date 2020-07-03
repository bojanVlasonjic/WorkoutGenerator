package rbs.wg.WorkoutGenerator.facts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.PropertyReactive;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.UserLevel;


@Getter
@Setter
@PropertyReactive
public class UserInformation {

    private boolean upperBodyWorked;
    private UserLevel userLevel;
    private double weight;

    private double repetitionFactor;
    private double workLoadFactor;
    private int workIntervalFactor;

    public UserInformation() {
        this.repetitionFactor = 1;
        this.workLoadFactor = 1;
        this.workIntervalFactor = 0;
    }

    public UserInformation(AppUser user) {
        this.upperBodyWorked = user.isUpperBodyWorked();
        this.userLevel = user.getUserLevel();
        this.weight = user.getWeight();

        this.repetitionFactor = user.getRepetitionFactor();
        this.workLoadFactor = user.getWorkLoadFactor();
        this.workIntervalFactor = user.getWorkIntervalFactor();
    }
}
