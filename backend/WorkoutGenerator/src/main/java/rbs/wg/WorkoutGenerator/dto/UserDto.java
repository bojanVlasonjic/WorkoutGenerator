package rbs.wg.WorkoutGenerator.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.UserLevel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Weight is required")
    private double weight;

    @NotNull(message = "User level is required")
    private UserLevel userLevel;

    private boolean isBanned;

    private double repetitionFactor;
    private double workLoadFactor;
    private int workIntervalFactor;


    public UserDto(AppUser user) {

        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.weight = user.getWeight();
        this.userLevel = user.getUserLevel();

        this.repetitionFactor = user.getRepetitionFactor();
        this.workLoadFactor = user.getWorkLoadFactor();
        this.workIntervalFactor = user.getWorkIntervalFactor();

        this.isBanned = user.isBanned();

    }
}
