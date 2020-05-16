package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rbs.wg.WorkoutGenerator.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
