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

        // TODO: utvrdi prethodnu grupu misica
        WorkoutProcessingDto workoutProcessing = new WorkoutProcessingDto();
        KieSession workoutSession = kieContainer.newKieSession("WGSession");
        workoutSession.setGlobal("random", random);

        workoutSession.insert(user);
        workoutSession.insert(workoutProcessing);
        workoutSession.insert(workoutRequest);
        workoutSession.fireAllRules();

        createWorkout(workoutRequest, workoutProcessing);
        workoutSession.dispose();

        return workoutProcessing;
    }

    public void createWorkout(WorkoutRequestDto workoutRequest, WorkoutProcessingDto workoutProcessing) {

        workoutProcessing.setNextMuscleGroup(MuscleGroup.CHEST);
        //List<Exercise> exercises = new ArrayList<>();

        // mapa koja sadrzi opremu i odgovarajuce vezbe za tu opremu
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

        List<Exercise> exercises = selectExercises(equipmentExercises, workoutProcessing.getNumOfExercises());
    }

    public List<Exercise> selectExercises(Map<Equipment, List<Exercise>> equipmentExercises, int numOfExercises) {

        Equipment equipment;
        List<Exercise> exercises = new ArrayList<>();
        int equipmentSize = equipmentExercises.keySet().size();

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
