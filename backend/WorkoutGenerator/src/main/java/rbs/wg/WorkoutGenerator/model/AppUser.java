package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.UserDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser extends Person {

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private boolean isBanned = false;

    @Column(nullable = false)
    private double repetitionFactor = 1;

    @Column(nullable = false)
    private double workLoadFactor = 1;

    @Column(nullable = false)
    private int workIntervalFactor = 0;

    @Column(nullable = false)
    private boolean upperBodyWorked; // whether the user worked upper body or lower body last time

    @Enumerated(EnumType.ORDINAL)
    private UserLevel userLevel;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Workout> workouts;


    public AppUser(UserDto userDto, Set<Authority> authorities) {

        super(userDto, authorities);
        this.weight = userDto.getWeight();
        this.userLevel = userDto.getUserLevel();

        this.setFactors(userDto);
    }

    public void updateUser(UserDto userDto) {

        this.setName(userDto.getName());
        this.setPassword(userDto.getPassword());
        this.setWeight(userDto.getWeight());

        // if the user changed it's level, reset the intensity factors
        if(this.userLevel != userDto.getUserLevel()) {
            this.repetitionFactor = 1;
            this.workLoadFactor = 1;
            this.workIntervalFactor = 0;
        }

        this.userLevel = userDto.getUserLevel();

    }

    private void setFactors(UserDto userDto) {

        if(userDto.getRepetitionFactor() != 0) {
            this.repetitionFactor = userDto.getRepetitionFactor();
        }

        if(userDto.getWorkLoadFactor() != 0) {
            this.workLoadFactor = userDto.getWorkLoadFactor();
        }

        this.workIntervalFactor = userDto.getWorkIntervalFactor();

    }

}
