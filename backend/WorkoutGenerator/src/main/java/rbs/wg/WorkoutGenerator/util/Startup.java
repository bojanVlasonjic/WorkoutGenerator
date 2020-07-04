package rbs.wg.WorkoutGenerator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.model.Rule;
import rbs.wg.WorkoutGenerator.repository.RuleRepository;

@Service
public class Startup {

    @Autowired
    private KieSessionDynamic kieSessionDynamic;

    @Autowired
    private RuleRepository ruleRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void createSessionWithOldRules() {
        kieSessionDynamic.createSessionFromOldRules();
    }
}
