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
        StringBuilder sb = new StringBuilder();

        for(Rule rule: ruleRepository.findAll()) {

            if(sb.toString().equals("")) {
                sb.append(rule.getContent());
            } else {
                sb.append(rule.getContent().substring(rule.getContent().indexOf("rule")));
            }

            sb.append("\n");

        }

        System.out.println(sb.toString());
        kieSessionDynamic.createSessionFromDrl(sb.toString());
    }
}
