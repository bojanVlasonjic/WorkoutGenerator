package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.UserDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.Authority;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;
import rbs.wg.WorkoutGenerator.repository.AuthorityRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private AuthorityRepository authorityRepo;


    public List<UserDto> getAllUsers() {
        return userRepo
                .findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UserDto getUserByEmail(String email) {

        Optional<AppUser> optUser = this.findUserByEmail(email);

        if(optUser.isPresent()) {
            return new UserDto(optUser.get());
        }

        throw new ApiNotFoundException("User not found");
    }

    public UserDto registerUser(UserDto userDto) {

        if (userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ApiBadRequestException("Email is already in use");
        }

        Set<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(authorityRepo.findById(-2L).get());

        AppUser user = userRepo.save(new AppUser(userDto, userAuthorities));
        return new UserDto(user);

    }

    public UserDto updateUser(UserDto userDto) {

        Optional<AppUser> optUser = this.findUserByEmail(userDto.getEmail());

        if(optUser.isPresent()) {
            optUser.get().updateUser(userDto);
            saveUser(optUser.get());
            return userDto;
        }

        throw new ApiNotFoundException("User not found");

    }

    public UserDto changeStatus(Long userId) {

        AppUser user = userRepo
                .findById(userId)
                .orElseThrow(() -> new ApiNotFoundException("User not found"));

        user.setBanned(!user.isBanned());
        return new UserDto(this.saveUser(user));

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
