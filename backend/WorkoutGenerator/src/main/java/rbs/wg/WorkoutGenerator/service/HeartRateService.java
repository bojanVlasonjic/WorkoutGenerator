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

    private Map<Long, KieSession> userSessions = new HashMap<>();


    public boolean startSimulation(Long userId) {

        if(userSessions.containsKey(userId)) {
            return false;
        }

        userSessions.put(userId, kieContainer.newKieSession("CEPSession"));
        return true;
    }

    public HeartRateDto sendHeartRate(HeartRateDto heartRateDto) {

        if(heartRateDto.getUserId() == null) {
            throw new ApiBadRequestException("User id not valid");
        }

        if(!userSessions.containsKey(heartRateDto.getUserId())) {
            throw new ApiBadRequestException("Simulation not started");
        }

        KieSession userSession = userSessions.get(heartRateDto.getUserId());
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

    public boolean stopSimulation(Long userId) {

        if(userSessions.containsKey(userId)) {
            // remove session
            userSessions.get(userId).dispose();
            userSessions.remove(userId);

            return true;
        }

        return false;

    }

}
