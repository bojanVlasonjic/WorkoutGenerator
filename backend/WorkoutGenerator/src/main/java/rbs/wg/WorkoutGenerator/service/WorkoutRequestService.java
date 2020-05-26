package rbs.wg.WorkoutGenerator.service;

import org.apache.commons.collections4.ListUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.facts.UserInformation;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;
import rbs.wg.WorkoutGenerator.facts.WorkoutRequest;
import rbs.wg.WorkoutGenerator.model.*;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;
import rbs.wg.WorkoutGenerator.repository.ExerciseRepository;

import java.util.*;

@Service
public class WorkoutRequestService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private ExerciseRepository exerciseRepo;

    @Autowired
    private Random random;


    public WorkoutDto processWorkout(WorkoutRequest workoutRequest) {

        AppUser user = this.userRepo
                .findById(workoutRequest.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Failed to find user with id " + workoutRequest.getUserId()));

        // default equipment is none
        workoutRequest.getSpecifiedEquipment().add(Equipment.NONE);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        UserInformation userInfo = new UserInformation(user);

        // prep session
        KieSession workoutSession = kieContainer.newKieSession("WGSession");
        workoutSession.setGlobal("random", random);

        // insert facts
        workoutSession.insert(userInfo);
        workoutSession.insert(workoutProcessing);
        workoutSession.insert(workoutRequest);

        // trigger rules and destroy session
        workoutSession.fireAllRules();
        workoutSession.dispose();

        System.out.println(workoutProcessing.getNextMuscleGroups()[0] + " " + workoutProcessing.getNextMuscleGroups()[1]);

        return createWorkout(workoutRequest, workoutProcessing, user);
    }

    public WorkoutDto createWorkout(WorkoutRequest workoutRequest,
                                    WorkoutProcessing workoutProcessing,
                                    AppUser user) {

        Map<Equipment, List<Exercise>> equipmentExercises;
        MuscleGroup[] muscleGroups = MuscleGroup.values();

        switch(workoutRequest.getWorkoutType()) {
            case STRENGTH:
                // select exercises for the first muscle group
                equipmentExercises = getStrengthExercises(workoutRequest,
                        muscleGroups[workoutProcessing.getNextMuscleGroups()[0]]);
                List<Exercise> firstGroupExercises = selectExercises(equipmentExercises,
                        workoutProcessing.getNumOfExercises()/2);

                // select exercises for the second muscle group
                equipmentExercises = getStrengthExercises(workoutRequest,
                        muscleGroups[workoutProcessing.getNextMuscleGroups()[1]]);
                List<Exercise> secondGroupExercises = selectExercises(equipmentExercises,
                        workoutProcessing.getNumOfExercises()/2 + workoutProcessing.getNumOfExercises()%2);

                return workoutService.createStrengthWorkout(
                       ListUtils.union(firstGroupExercises, secondGroupExercises),
                        workoutProcessing,
                        user
                );

            case CONDITIONING:
                equipmentExercises = getConditioningExercises(workoutRequest);
                return workoutService.createConditioningWorkout(
                        selectExercises(equipmentExercises, workoutProcessing.getNumOfIntervals()/2),
                        workoutProcessing,
                        user
                );

            case COMBO:
                equipmentExercises = getConditioningExercises(workoutRequest);
                List<Exercise> conditioningExercises = selectExercises(
                        equipmentExercises, workoutProcessing.getNumOfIntervals()/2);

                equipmentExercises = getStrengthExercises(workoutRequest,
                        muscleGroups[workoutProcessing.getNextMuscleGroups()[0]]);
                List<Exercise> strengthExercises = selectExercises(
                        equipmentExercises, workoutProcessing.getNumOfExercises());

                return workoutService.createComboWorkout(strengthExercises, conditioningExercises,
                        workoutProcessing, user);

            default:
                throw new ApiBadRequestException("Workout type not recognized");
        }

    }

    public Map<Equipment, List<Exercise>> getConditioningExercises(WorkoutRequest workoutRequest) {

        Map<Equipment, List<Exercise>> equipmentExercises = new HashMap<>();
        List<Exercise> matchingExercises;

        for (Equipment equipment : workoutRequest.getSpecifiedEquipment()) {

            matchingExercises = ListUtils.union(
                    exerciseRepo
                            .findByEquipmentAndExerciseType(equipment, ExerciseType.CONDITIONING),
                    exerciseRepo
                            .findByEquipmentAndExerciseType(equipment, ExerciseType.COMBO)
            );
            if(matchingExercises.size() > 0) {
                equipmentExercises.put(equipment, matchingExercises);
            }
        }

        return equipmentExercises;
    }

    public Map<Equipment, List<Exercise>> getStrengthExercises(WorkoutRequest workoutRequest,
                                                               MuscleGroup targetedMuscle) {
        Map<Equipment, List<Exercise>> equipmentExercises = new HashMap<>();
        List<Exercise> matchingExercises;
        for (Equipment equipment : workoutRequest.getSpecifiedEquipment()) {

            matchingExercises = ListUtils.union(
                    exerciseRepo
                            .findByEquipmentAndExerciseTypeAndTargetedMusclesContaining(equipment,
                                    ExerciseType.STRENGTH, targetedMuscle),
                    exerciseRepo
                            .findByEquipmentAndExerciseTypeAndTargetedMusclesContaining(equipment,
                                    ExerciseType.COMBO, targetedMuscle)
            );
            if(matchingExercises.size() > 0) {
                equipmentExercises.put(equipment, matchingExercises);
            }
        }

        return equipmentExercises;
    }

    public List<Exercise> selectExercises(Map<Equipment, List<Exercise>> equipmentExercises, int numOfExercises) {

        Equipment equipment;
        List<Exercise> exercises = new ArrayList<>();
        int equipmentSize = equipmentExercises.keySet().size();

        // if no exercises were found
        if(equipmentSize == 0) {
            throw new ApiNotFoundException("Failed to find exercises");
        }

        for(int i = 0; i < numOfExercises; i++) {

            // randomly select equipment
            equipment = (Equipment) equipmentExercises.keySet().toArray()[random.nextInt(equipmentSize)];

            // randomly select exercise with that equipment
            exercises.add(
                    equipmentExercises.get(equipment).get(
                            random.nextInt(equipmentExercises.get(equipment).size())
                    )
            );
        }

        return exercises;

    }

}
