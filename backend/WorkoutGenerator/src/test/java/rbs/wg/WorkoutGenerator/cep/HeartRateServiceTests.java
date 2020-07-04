package rbs.wg.WorkoutGenerator.cep;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rbs.wg.WorkoutGenerator.events.HeartRateEvent;
import rbs.wg.WorkoutGenerator.events.NotificationMessage;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeartRateServiceTests {

    @Autowired
    private KieContainer kieContainer;

    private final String speedUpMessage = "Pick up the pace!";
    private final String maintainPaceMessage = "Good work, keep maintaining this pace!";


    private FactHandle insertEvents(List<HeartRateEvent> heartRateEvents,
                                    NotificationMessage notificationMessage,
                                    KieSession testSession) {

        FactHandle msgFact = testSession.insert(notificationMessage);
        for(HeartRateEvent event: heartRateEvents) {
            testSession.insert(event);
        }

        return msgFact;

    }

    private List<HeartRateEvent> prepEvents(int startVal, int endVal, int increment, int goal) {

        List<HeartRateEvent> heartRateEvents = new ArrayList<>();
        HeartRateEvent event;
        for(int i = startVal; i < endVal; i += increment) {
            event = new HeartRateEvent();
            event.setHeartRate(i);
            event.setGoal(goal);
            heartRateEvents.add(event);
        }

        return heartRateEvents;
    }

    @Test
    public void givenFirstEvent_whenAddingEvent_doNothing() {

        KieSession testSession = kieContainer.newKieSession("TestCEPSession");

        HeartRateEvent heartRateDto = new HeartRateEvent();
        heartRateDto.setHeartRate(100);
        heartRateDto.setGoal(100);

        this.insertEvents(Collections.singletonList(heartRateDto), new NotificationMessage(), testSession);
        assertEquals(0, testSession.fireAllRules());

        testSession.dispose();

    }

    @Test
    public void givenMultipleEvents_whenHeartRateLowerThanGoal_notifyUserToSpeedUp() {

        KieSession testSession = kieContainer.newKieSession("TestCEPSession");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setMessage(null);

        this.insertEvents(prepEvents(30, 151, 30, 152),
                notificationMessage, testSession);

        assertEquals(1, testSession.fireAllRules());
        assertEquals(this.speedUpMessage, notificationMessage.getMessage());

        testSession.dispose();

    }

    @Test
    public void givenMultipleEvents_whenHeartRateHigherThanGoal_notifyUserToMaintainPace() {

        KieSession testSession = kieContainer.newKieSession("TestCEPSession");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setMessage(null);

        this.insertEvents(prepEvents(30, 181, 30, 50),
                notificationMessage, testSession);

        assertEquals(1, testSession.fireAllRules());
        assertEquals(this.maintainPaceMessage, notificationMessage.getMessage());

        testSession.dispose();

    }

    @Test
    public void givenMultipleEvents_whenHeartRateSwitches_doNotNotify() {

        KieSession testSession = kieContainer.newKieSession("TestCEPSession");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setMessage(null);
        int goal = 165;

        // send multiple lower events to notify user to speed up
        this.insertEvents(prepEvents(30, goal-10, 30, goal),
                notificationMessage, testSession);
        assertEquals(1, testSession.fireAllRules());
        assertEquals(this.speedUpMessage, notificationMessage.getMessage());

        // send an event with a higher value, no message should be sent
        HeartRateEvent higherValEvent = new HeartRateEvent();
        higherValEvent.setGoal(goal);
        higherValEvent.setHeartRate(goal + 5);
        testSession.insert(higherValEvent);

        assertEquals(0, testSession.fireAllRules());
        testSession.dispose();
    }

    @Test
    public void givenMultipleEvents_whenHeartRateSwitchesAndStabilizes_notifyUser() {

        KieSession testSession = kieContainer.newKieSession("TestCEPSession");

        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setMessage(null);
        int goal = 165;

        // send multiple events with a lower heart rate, notify user to speed up
        FactHandle msgFact = this.insertEvents(prepEvents(30, goal-10, 30, goal),
                notificationMessage, testSession);
        assertEquals(1, testSession.fireAllRules());
        assertEquals(this.speedUpMessage, notificationMessage.getMessage());

        // message is removed after firing all rules
        notificationMessage.setMessage(null);
        testSession.delete(msgFact);

        // send multiple events with a higher heart rate, notify user to maintain pace
        this.insertEvents(prepEvents(goal+1, goal+6, 1, goal),
                notificationMessage, testSession);
        assertEquals(1, testSession.fireAllRules());
        assertEquals(this.maintainPaceMessage, notificationMessage.getMessage());

        testSession.dispose();
    }

}
