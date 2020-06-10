package rbs.wg.WorkoutGenerator.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.PropertyReactive;
import org.kie.api.definition.type.Role;


@PropertyReactive
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationMessage {

    private String message;
}

