package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;

import java.util.Optional;

@Service
public class AppUserService {


    @Autowired
    private AppUserRepository userRepo;

    public Optional<AppUser> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

    public AppUser saveUser(AppUser user) {
        return userRepo.save(user);
    }
}
