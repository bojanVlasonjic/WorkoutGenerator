package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbs.wg.WorkoutGenerator.model.Rule;

import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    Optional<Rule> findByName(String name);
}
