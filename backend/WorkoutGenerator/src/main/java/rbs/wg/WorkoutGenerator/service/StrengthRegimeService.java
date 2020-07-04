package rbs.wg.WorkoutGenerator.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.StrengthRegimeDto;
import rbs.wg.WorkoutGenerator.facts.UserInformation;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;
import rbs.wg.WorkoutGenerator.facts.WorkoutRequest;
import rbs.wg.WorkoutGenerator.model.AppUser;
import rbs.wg.WorkoutGenerator.model.StrengthRegime;

import java.util.List;
import java.util.Random;

@Service
public class StrengthRegimeService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private Random random;


    public List<StrengthRegime> determineWorkLoad(List<StrengthRegime> strengthRegimes, AppUser user) {

        // prep session
        KieSession workoutSession = kieContainer.newKieSession("WGSession");
        workoutSession.setGlobal("random", random);

        // insert facts
        workoutSession.insert(new UserInformation(user));
        for (StrengthRegime strengthRegime: strengthRegimes) {
            workoutSession.insert(strengthRegime);
        }

        // trigger rules and destroy session
        workoutSession.getAgenda().getAgendaGroup("workLoad").setFocus();
        workoutSession.fireAllRules();
        workoutSession.dispose();

        return strengthRegimes;

    }
}
