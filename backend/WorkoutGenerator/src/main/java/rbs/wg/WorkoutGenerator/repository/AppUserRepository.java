package rbs.wg.WorkoutGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbs.wg.WorkoutGenerator.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
