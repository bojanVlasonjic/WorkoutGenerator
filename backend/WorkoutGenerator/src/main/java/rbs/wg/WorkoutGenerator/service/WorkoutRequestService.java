package rbs.wg.WorkoutGenerator.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.Workout;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;

@Service
public class WorkoutRequestService {

    @Autowired
    private KieSession workoutSession;

    @Autowired
    private AppUserRepository userRepo;


    public WorkoutProcessingDto processWorkout(WorkoutRequestDto workoutRequest) {

        WorkoutProcessingDto workoutProcessing = new WorkoutProcessingDto();
        AppUser user = this.userRepo
                .findById(workoutRequest.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Failed to find user with id " + workoutRequest.getUserId()));

        // TODO: utvrdi prethodnu grupu misica

        workoutSession.insert(user);
        workoutSession.insert(workoutProcessing);
        workoutSession.insert(workoutRequest);
        workoutSession.fireAllRules();

        return workoutProcessing;
    }
}
