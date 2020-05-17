package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbs.wg.WorkoutGenerator.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
