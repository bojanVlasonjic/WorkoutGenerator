package rbs.wg.WorkoutGenerator.service;

import org.apache.commons.collections4.ListUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
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


    public WorkoutDto processWorkout(WorkoutRequestDto workoutRequest) {

        AppUser user = this.userRepo
                .findById(workoutRequest.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Failed to find user with id " + workoutRequest.getUserId()));

        // default equipment is none
        workoutRequest.getSpecifiedEquipment().add(Equipment.NONE);

        // prep session
        WorkoutProcessingDto workoutProcessing = new WorkoutProcessingDto();
        KieSession workoutSession = kieContainer.newKieSession("WGSession");
        workoutSession.setGlobal("random", random);

        // trigger rules
        workoutSession.insert(user);
        workoutSession.insert(workoutProcessing);
        workoutSession.insert(workoutRequest);

        workoutSession.fireAllRules();
        workoutSession.dispose();

        return createWorkout(workoutRequest, workoutProcessing, user);
    }

    public WorkoutDto createWorkout(WorkoutRequestDto workoutRequest,
                                    WorkoutProcessingDto workoutProcessing,
                                    AppUser user) {

        Map<Equipment, List<Exercise>> equipmentExercises;

        switch(workoutRequest.getWorkoutType()) {
            case STRENGTH:
                workoutProcessing.setNextMuscleGroup(workoutRequest.getTargetedMuscle()); //temporary
                equipmentExercises = getStrengthExercises(workoutRequest, workoutProcessing);
                return workoutService.createStrengthWorkout(
                        selectExercises(equipmentExercises, workoutProcessing.getNumOfExercises()),
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
                workoutProcessing.setNextMuscleGroup(workoutRequest.getTargetedMuscle()); //temporary
                equipmentExercises = getConditioningExercises(workoutRequest);
                List<Exercise> conditioningExercises = selectExercises(
                        equipmentExercises, workoutProcessing.getNumOfIntervals()/2);

                equipmentExercises = getStrengthExercises(workoutRequest, workoutProcessing);
                List<Exercise> strengthExercises = selectExercises(
                        equipmentExercises, workoutProcessing.getNumOfExercises());

                return workoutService.createComboWorkout(strengthExercises, conditioningExercises,
                        workoutProcessing, user);

            default:
                throw new ApiBadRequestException("Workout type not recognized");
        }

    }

    public Map<Equipment, List<Exercise>> getConditioningExercises(WorkoutRequestDto workoutRequest) {

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

    public Map<Equipment, List<Exercise>> getStrengthExercises(WorkoutRequestDto workoutRequest,
                                                               WorkoutProcessingDto workoutProcessing) {
        Map<Equipment, List<Exercise>> equipmentExercises = new HashMap<>();
        List<Exercise> matchingExercises;
        for (Equipment equipment : workoutRequest.getSpecifiedEquipment()) {

            matchingExercises = ListUtils.union(
                    exerciseRepo
                            .findByEquipmentAndExerciseTypeAndTargetedMusclesContaining(equipment,
                                    ExerciseType.STRENGTH, workoutProcessing.getNextMuscleGroup()),
                    exerciseRepo
                            .findByEquipmentAndExerciseTypeAndTargetedMusclesContaining(equipment,
                                    ExerciseType.COMBO, workoutProcessing.getNextMuscleGroup())
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
            return exercises;
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
