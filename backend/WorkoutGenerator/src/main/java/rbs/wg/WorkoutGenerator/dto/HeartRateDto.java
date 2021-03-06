package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HeartRateDto {

    private int heartRate;
    private int goal;

    private String userEmail;
    private String notificationMessage;


}
