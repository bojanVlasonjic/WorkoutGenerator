package rbs.wg.WorkoutGenerator.service;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.HeartRateDto;
import rbs.wg.WorkoutGenerator.events.HeartRateEvent;
import rbs.wg.WorkoutGenerator.events.NotificationMessage;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class HeartRateService {

    @Autowired
    private KieContainer kieContainer;

    private Map<String, KieSession> userSessions = new HashMap<>();


    public boolean startSimulation(String email) {

        if(userSessions.containsKey(email)) {
            return false;
        }

        userSessions.put(email, kieContainer.newKieSession("CEPSession"));
        return true;
    }

    public HeartRateDto sendHeartRate(HeartRateDto heartRateDto) {

        if(heartRateDto.getUserEmail() == null) {
            throw new ApiBadRequestException("User email is not valid");
        }

        if(!userSessions.containsKey(heartRateDto.getUserEmail())) {
            throw new ApiBadRequestException("Simulation not started");
        }

        KieSession userSession = userSessions.get(heartRateDto.getUserEmail());
        FactHandle msgFact = userSession.insert(new NotificationMessage());
        userSession.insert(new HeartRateEvent(heartRateDto));
        userSession.fireAllRules();

        // extract message
        Collection<?> messages = userSession.getObjects(new ClassObjectFilter(NotificationMessage.class));
        Optional<NotificationMessage> msg = (Optional<NotificationMessage>) messages.stream().findFirst();

        // if the message was extracted, send it back to the user
        msg.ifPresent(notificationMessage -> heartRateDto.setNotificationMessage(notificationMessage.getMessage()));
        userSession.delete(msgFact);

        return heartRateDto;

    }

    public boolean stopSimulation(String email) {

        if(userSessions.containsKey(email)) {
            // remove session
            userSessions.get(email).dispose();
            userSessions.remove(email);

            return true;
        }

        return false;

    }

}
