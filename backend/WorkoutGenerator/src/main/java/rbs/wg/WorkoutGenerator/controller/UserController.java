package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.UserDto;
import rbs.wg.WorkoutGenerator.service.AppUserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AppUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {

        return new ResponseEntity<>(
                userService.registerUser(userDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<UserDto> changeBanStatus(@PathVariable Long id) {
        return ResponseEntity.ok(userService.changeStatus(id));
    }



}
