package rbs.wg.WorkoutGenerator.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rbs.wg.WorkoutGenerator.dto.ReviewDto;
import rbs.wg.WorkoutGenerator.facts.ReviewComplaint;
import rbs.wg.WorkoutGenerator.model.AppUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
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
    public void givenMediumExertionLevel_whenComplaint_doNothing() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(6);
        reviewDto.getComplaints().add(ReviewComplaint.REPETITIONS);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_INTERVAL);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_LOAD);

        double previousRepFactor = appUser.getRepetitionFactor();
        double previousIntervalFactor = appUser.getWorkIntervalFactor();
        double previousLoadFactor = appUser.getWorkLoadFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(0, rulesFired);
        assertEquals(previousRepFactor, appUser.getRepetitionFactor());
        assertEquals(previousIntervalFactor, appUser.getWorkIntervalFactor());
        assertEquals(previousLoadFactor, appUser.getWorkLoadFactor());

        testSession.dispose();
    }

    /*** ***********************
     * SINGLE COMPLAINT TESTS
     * ************************/

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

        testSession.dispose();

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

        testSession.dispose();

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

        testSession.dispose();

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

        testSession.dispose();

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

        testSession.dispose();

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

        testSession.dispose();

    }


    /*** ***********************
     * MULTIPLE COMPLAINTS TESTS
     * ************************/

    @Test
    public void givenLowExertionLevel_whenMultipleComplaints_increaseAllFactors() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(2);
        reviewDto.getComplaints().add(ReviewComplaint.REPETITIONS);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_INTERVAL);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_LOAD);

        double previousRepFactor = appUser.getRepetitionFactor();
        double previousIntervalFactor = appUser.getWorkIntervalFactor();
        double previousLoadFactor = appUser.getWorkLoadFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(3, rulesFired);
        assertTrue(previousRepFactor < appUser.getRepetitionFactor());
        assertTrue(previousIntervalFactor < appUser.getWorkIntervalFactor());
        assertTrue(previousLoadFactor < appUser.getWorkLoadFactor());

        testSession.dispose();
    }

    @Test
    public void givenHighExertionLevel_whenMultipleComplaints_decreaseAllFactors() {

        AppUser appUser = new AppUser();
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setExertionLevel(9);
        reviewDto.getComplaints().add(ReviewComplaint.REPETITIONS);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_INTERVAL);
        reviewDto.getComplaints().add(ReviewComplaint.WORK_LOAD);

        double previousRepFactor = appUser.getRepetitionFactor();
        double previousIntervalFactor = appUser.getWorkIntervalFactor();
        double previousLoadFactor = appUser.getWorkLoadFactor();

        KieSession testSession = this.prepSessionWithFacts(reviewDto, appUser);
        int rulesFired = testSession.fireAllRules();

        assertEquals(3, rulesFired);
        assertTrue(previousRepFactor > appUser.getRepetitionFactor());
        assertTrue(previousIntervalFactor > appUser.getWorkIntervalFactor());
        assertTrue(previousLoadFactor > appUser.getWorkLoadFactor());

        testSession.dispose();

    }


}
