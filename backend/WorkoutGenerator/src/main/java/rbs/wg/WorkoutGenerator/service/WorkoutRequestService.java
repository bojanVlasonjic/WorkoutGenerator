package rbs.wg.WorkoutGenerator.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.WorkoutProcessingDto;
import rbs.wg.WorkoutGenerator.dto.WorkoutRequestDto;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.repository.AppUserRepository;

import java.util.Random;

@Service
public class WorkoutRequestService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private AppUserRepository userRepo;

    //@Autowired
    //private Random random;


    public WorkoutProcessingDto processWorkout(WorkoutRequestDto workoutRequest) {

        AppUser user = this.userRepo
                .findById(workoutRequest.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Failed to find user with id " + workoutRequest.getUserId()));

        // TODO: utvrdi prethodnu grupu misica
        WorkoutProcessingDto workoutProcessing = new WorkoutProcessingDto();
        KieSession workoutSession = kieContainer.newKieSession("WGSession");
        workoutSession.setGlobal("random", new Random());

        workoutSession.insert(user);
        workoutSession.insert(workoutProcessing);
        workoutSession.insert(workoutRequest);

        workoutSession.fireAllRules();
        workoutSession.dispose();

        return workoutProcessing;
    }
}
