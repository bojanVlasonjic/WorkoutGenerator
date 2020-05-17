package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;

@Service
public class AppUserService {


    @Autowired
    private AppUserRepository userRepo;


}
