package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.UserLevel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Weight is required")
    private double weight;

    @NotNull(message = "User level is required")
    private UserLevel userLevel;

    private double repetitionFactor;
    private double workLoadFactor;
    private int workIntervalFactor;

    private List<Equipment> equipment;

    public UserDto(AppUser user) {

        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.weight = user.getWeight();
        this.userLevel = user.getUserLevel();

        this.repetitionFactor = user.getRepetitionFactor();
        this.workLoadFactor = user.getWorkLoadFactor();
        this.workIntervalFactor = user.getWorkIntervalFactor();

        this.equipment = user.getEquipment();
    }
}
