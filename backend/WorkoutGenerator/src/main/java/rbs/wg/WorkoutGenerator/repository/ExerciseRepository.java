package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.Exercise;
import rbs.wg.WorkoutGenerator.model.ExerciseType;
import rbs.wg.WorkoutGenerator.model.MuscleGroup;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // used for strength workouts
    List<Exercise> findByEquipmentAndExerciseTypeAndTargetedMusclesContaining(Equipment equipment,
                                                                              ExerciseType exerciseType,
                                                                              MuscleGroup muscleGroup);

    // user for conditioning workouts
    List<Exercise> findByEquipmentAndExerciseType(Equipment equipment, ExerciseType exerciseType);
}
