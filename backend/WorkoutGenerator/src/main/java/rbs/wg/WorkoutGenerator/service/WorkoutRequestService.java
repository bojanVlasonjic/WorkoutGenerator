package rbs.wg.WorkoutGenerator.service;

import org.apache.commons.collections4.ListUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
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
    private AppUserRepository userRepo;

    @Autowired
    private ExerciseRepository exerciseRepo;

    @Autowired
    private Random random;


    public WorkoutProcessingDto processWorkout(WorkoutRequestDto workoutRequest) {

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

        // create workout based on triggered rules
        workoutProcessing.setSelectedExercises(createWorkout(workoutRequest, workoutProcessing));
        workoutSession.dispose();

        return workoutProcessing;
    }

    public List<Exercise> createWorkout(WorkoutRequestDto workoutRequest, WorkoutProcessingDto workoutProcessing) {

        Map<Equipment, List<Exercise>> equipmentExercises = new HashMap<>();

        switch(workoutRequest.getWorkoutType()) {
            case STRENGTH:
                workoutProcessing.setNextMuscleGroup(workoutRequest.getTargetedMuscle()); // temporary, rules will determine
                equipmentExercises = getStrengthExercises(workoutRequest, workoutProcessing);
                break;

            case CONDITIONING:
                equipmentExercises = getConditioningExercises(workoutRequest, workoutProcessing);
                break;

            default:
                break;
        }


        return selectExercises(equipmentExercises, workoutProcessing.getNumOfExercises());
    }

    public Map<Equipment, List<Exercise>> getConditioningExercises(WorkoutRequestDto workoutRequest,
                                                                   WorkoutProcessingDto workoutProcessing) {
        Map<Equipment, List<Exercise>> equipmentExercises = new HashMap<>();
        List<Exercise> matchingExercises;

        for (Equipment equipment : workoutRequest.getSpecifiedEquipment()) {

            matchingExercises = ListUtils.union(
                    exerciseRepo
                            .findByEquipmentAndExerciseType(equipment, workoutRequest.getWorkoutType()),
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
                                    workoutRequest.getWorkoutType(), workoutProcessing.getNextMuscleGroup()),
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
