package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rbs.wg.WorkoutGenerator.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
