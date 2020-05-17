package rbs.wg.WorkoutGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.repository.WorkoutRepository;

@Service
public class WorkoutService {


    @Autowired
    private WorkoutRepository workoutRepository;




}

