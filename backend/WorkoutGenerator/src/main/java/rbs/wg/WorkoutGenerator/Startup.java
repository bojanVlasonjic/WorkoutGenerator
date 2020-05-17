package rbs.wg.WorkoutGenerator;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Startup {

    @Autowired
    private KieSession workoutSession;

    @EventListener(ApplicationReadyEvent.class)
    public void initGlobalVariables() {
        workoutSession.setGlobal("random", new Random());
    }

}
