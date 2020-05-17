package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbs.wg.WorkoutGenerator.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
