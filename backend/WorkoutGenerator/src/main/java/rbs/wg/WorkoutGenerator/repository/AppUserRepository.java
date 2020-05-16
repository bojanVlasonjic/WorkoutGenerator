package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rbs.wg.WorkoutGenerator.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
