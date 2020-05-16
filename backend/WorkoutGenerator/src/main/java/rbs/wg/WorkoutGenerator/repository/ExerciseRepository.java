package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rbs.wg.WorkoutGenerator.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
