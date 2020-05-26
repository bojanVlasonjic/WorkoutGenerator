package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;
import rbs.wg.WorkoutGenerator.model.*;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;
import rbs.wg.WorkoutGenerator.repository.WorkoutRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private AppUserRepository appUserRepository;


    public List<WorkoutDto> getUserWorkouts(Long userId) {

        return workoutRepository
                .findByUserId(userId)
                .stream()
                .map(WorkoutDto::new)
                .collect(Collectors.toList());

    }

    public WorkoutDto createUserWorkout(WorkoutDto workoutDto) {

        AppUser user = this.appUserRepository
                .findById(workoutDto.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("User not found"));

        Workout workout = new Workout(workoutDto, user);
        workout.setTemporaryId(null); // no need for this anymore
        workout = workoutRepository.save(workout);

        // if strength workout switch to lower/upper body for next workout
        if(workoutDto.getStrengthWorkoutDto() != null) {
            user.setUpperBodyWorked(!user.isUpperBodyWorked());
            appUserRepository.save(user);
        }

        return new WorkoutDto(workout);

    }


    /** *****
     * Workout generating
     ** *****/

    public WorkoutDto createStrengthWorkout(List<Exercise> exercises,
                                            WorkoutProcessing workoutProcessing,
                                            AppUser user) {

        Workout workout = new Workout();
        workout.setTemporaryId(UUID.randomUUID());

        StrengthWorkout strengthWorkout = new StrengthWorkout(workout, workoutProcessing, exercises);
        workout.initWorkout(strengthWorkout,null, user, new Date());

        return new WorkoutDto(workout);

    }

    public WorkoutDto createConditioningWorkout(List<Exercise> exercises,
                                                WorkoutProcessing workoutProcessing,
                                                AppUser user) {

        Workout workout = new Workout();
        workout.setTemporaryId(UUID.randomUUID());

        ConditioningWorkout conditioningWorkout = new ConditioningWorkout(workout, workoutProcessing, exercises);
        workout.initWorkout(null, conditioningWorkout, user, new Date());

        return new WorkoutDto(workout);

    }

    public WorkoutDto createComboWorkout(List<Exercise> strengthExercises,
                                         List<Exercise> conditioningExercises,
                                         WorkoutProcessing workoutProcessing,
                                         AppUser user) {

        Workout workout = new Workout();
        workout.setTemporaryId(UUID.randomUUID());

        StrengthWorkout strengthWorkout = new StrengthWorkout(workout, workoutProcessing, strengthExercises);
        ConditioningWorkout conditioningWorkout = new ConditioningWorkout(workout,
                workoutProcessing, conditioningExercises);

        workout.initWorkout(strengthWorkout, conditioningWorkout, user, new Date());

        return new WorkoutDto(workout);

    }



}

