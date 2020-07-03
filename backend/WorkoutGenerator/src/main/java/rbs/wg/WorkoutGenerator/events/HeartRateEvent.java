package rbs.wg.WorkoutGenerator.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import rbs.wg.WorkoutGenerator.dto.HeartRateDto;

@Role(Role.Type.EVENT)
@Expires("1m")
@NoArgsConstructor
@Getter
@Setter
public class HeartRateEvent {

    private int heartRate;
    private int goal;

    public HeartRateEvent(HeartRateDto heartRateDto) {

        this.heartRate = heartRateDto.getHeartRate();
        this.goal = heartRateDto.getGoal();
    }

}
