package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.model.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
