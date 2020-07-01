package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.UserDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.Authority;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;
import rbs.wg.WorkoutGenerator.repository.AuthorityRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private AuthorityRepository authorityRepo;


    public UserDto registerUser(UserDto userDto) {

        if (userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ApiBadRequestException("Email is already in use");
        }

        Set<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(authorityRepo.findById(-2L).get());

        AppUser user = userRepo.save(new AppUser(userDto, userAuthorities));
        return new UserDto(user);

    }

    public Optional<AppUser> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public AppUser saveUser(AppUser user) {
        return userRepo.save(user);
    }
}
