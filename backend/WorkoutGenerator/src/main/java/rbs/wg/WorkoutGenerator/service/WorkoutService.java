package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.model.*;
import rbs.wg.WorkoutGenerator.repository.WorkoutRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {


    @Autowired
    private WorkoutRepository workoutRepository;



    public WorkoutDto createStrengthWorkout(List<Exercise> exercises,
                                            WorkoutProcessingDto workoutProcessing,
                                            AppUser user) {

        Workout workout = new Workout();
        workout.setTemporaryId(UUID.randomUUID());

        StrengthWorkout strengthWorkout = new StrengthWorkout(workout, workoutProcessing, exercises);
        workout.initWorkout(strengthWorkout,null, user, new Date());

        return new WorkoutDto(workout);

    }

    public WorkoutDto createConditioningWorkout(List<Exercise> exercises,
                                                WorkoutProcessingDto workoutProcessing,
                                                AppUser user) {

        Workout workout = new Workout();
        workout.setTemporaryId(UUID.randomUUID());

        ConditioningWorkout conditioningWorkout = new ConditioningWorkout(workout, workoutProcessing, exercises);
        workout.initWorkout(null, conditioningWorkout, user, new Date());

        return new WorkoutDto(workout);

    }

    public WorkoutDto createComboWorkout(List<Exercise> strengthExercises,
                                         List<Exercise> conditioningExercises,
                                         WorkoutProcessingDto workoutProcessing,
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

