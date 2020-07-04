package rbs.wg.WorkoutGenerator.util;

import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.model.Rule;
import rbs.wg.WorkoutGenerator.repository.RuleRepository;

import java.util.List;

public class KieSessionDynamic {

    @Autowired
    private KieServices kieServices;

    @Autowired
    private RuleRepository ruleRepository;

    private KieSession dynamicSession;

    public void createSessionFromDrl(String drl) {

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            StringBuilder sb = new StringBuilder();
            sb.append("Errors were found while verifying rule.");
            for (Message message : messages) {
                sb.append(message.getText());
            }

            throw new ApiBadRequestException(sb.toString());
        }

        dynamicSession =  kieHelper.build(kieServices.newKieBaseConfiguration()).newKieSession();
    }


    public String combinePreviousRules(String newDrl) {

        StringBuilder sb = new StringBuilder();
        sb.append(newDrl); // append first rule with imports
        sb.append("\n");

        for(Rule rule: ruleRepository.findAll()) {
            // skip imports and append only rule content
            sb.append(rule.getContent().substring(rule.getContent().indexOf("rule")));
            sb.append("\n");
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public void createSessionFromOldRules() {
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
        this.createSessionFromDrl(sb.toString());
    }


    public KieSession getDynamicSession() {
        return this.dynamicSession;
    }

}
