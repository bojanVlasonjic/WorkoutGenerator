package rbs.wg.WorkoutGenerator.rules;

import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rbs.wg.WorkoutGenerator.dto.ReviewDto;
import rbs.wg.WorkoutGenerator.facts.ReviewComplaint;
import rbs.wg.WorkoutGenerator.model.AppUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReviewAdjustingTests {


    @Autowired
    private KieContainer kieContainer;



    private KieSession prepSessionWithFacts(ReviewDto reviewDto,
                                            AppUser appUser) {

        KieSession testSession = kieContainer.newKieSession("WGTestSession");

        testSession.insert(reviewDto);
        testSession.insert(appUser);

        testSession.getAgenda().getAgendaGroup("review").setFocus();

        return testSession;
    }


    @Test
    public void givenLowExertionLevel_whenRepetitionComplaint_thenIncreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(2);
        reviewDto.getComplaints().add(ReviewComplaint.REPETITIONS);

        double previousFactor = appUser.getRepetitionFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor < appUser.getRepetitionFactor());

    }

    @Test
    public void givenHighExertionLevel_whenRepetitionComplaint_thenDecreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(9);
        reviewDto.getComplaints().add(ReviewComplaint.REPETITIONS);

        double previousFactor = appUser.getRepetitionFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor > appUser.getRepetitionFactor());

    }

    @Test
    public void givenLowExertionLevel_whenWorkLoadComplaint_thenIncreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(2);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_LOAD);

        double previousFactor = appUser.getWorkLoadFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor < appUser.getWorkLoadFactor());

    }

    @Test
    public void givenHighExertionLevel_whenWorkLoadComplaint_thenDecreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(9);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_LOAD);

        double previousFactor = appUser.getWorkLoadFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor > appUser.getWorkLoadFactor());

    }

    @Test
    public void givenLowExertionLevel_whenWorkIntervalComplaint_thenIncreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(2);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_INTERVAL);

        double previousFactor = appUser.getWorkIntervalFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor < appUser.getWorkIntervalFactor());

    }


    @Test
    public void givenHighExertionLevel_whenWorkIntervalComplaint_thenDecreaseRepetitionFactor() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(9);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_INTERVAL);

        double previousFactor = appUser.getWorkIntervalFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(1, rulesFired);
        assertTrue(previousFactor > appUser.getWorkIntervalFactor());

    }


}
