package rbs.wg.WorkoutGenerator.rules;

import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rbs.wg.WorkoutGenerator.facts.UserInformation;
import rbs.wg.WorkoutGenerator.facts.WorkoutProcessing;
import rbs.wg.WorkoutGenerator.facts.WorkoutRequest;
import rbs.wg.WorkoutGenerator.model.ExerciseType;
import rbs.wg.WorkoutGenerator.model.UserLevel;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WorkoutRequestServiceTests {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private Random random;


    private KieSession prepSessionWithFacts(UserInformation userInformation,
                                            WorkoutProcessing workoutProcessing,
                                            WorkoutRequest workoutRequest) {

        KieSession testSession = kieContainer.newKieSession("WGTestSession");
        testSession.setGlobal("random", random);

        testSession.insert(userInformation);
        testSession.insert(workoutProcessing);
        testSession.insert(workoutRequest);

        testSession.getAgenda().getAgendaGroup("workout").setFocus();

        return testSession;
    }


    /*************************
     *  NEXT MUSCLE GROUP TESTS
     **************************/


    @Test
    public void givenUpperBodyWorked_whenGeneratingWorkout_thenSelectLowerBodyMuscles() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUpperBodyWorked(true);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, new WorkoutRequest());
        int rulesFired = testSession.fireAllRules();

        // validate
        assertEquals(1, rulesFired);
        validateMuscleGroups(6, 9, workoutProcessing);

        testSession.dispose();
    }

    @Test
    public void givenLowerBodyWorked_whenGeneratingWorkout_thenSelectUpperBodyMuscles() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUpperBodyWorked(false);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, new WorkoutRequest());
        int rulesFired = testSession.fireAllRules();

        // validate
        assertEquals(1, rulesFired);
        validateMuscleGroups(0, 5, workoutProcessing);

        testSession.dispose();
    }


    /*************************
     *  USER LEVEL INTENSITY TESTS
     **************************/

    @Test
    public void givenUserLevelBeginner_whenGeneratingWorkout_thenSetLowIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.BEGINNER);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        WorkoutRequest workoutRequest = new WorkoutRequest();

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);
        this.validateUserLevelIntensity(3, 20, 10,
                4, 8, workoutProcessing);

        testSession.dispose();
    }

    @Test
    public void givenUserLevelIntermediate_whenGeneratingWorkout_thenSetMediumIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.INTERMEDIATE);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        WorkoutRequest workoutRequest = new WorkoutRequest();

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);
        this.validateUserLevelIntensity(4, 30, 15,
                6, 10, workoutProcessing);

        testSession.dispose();
    }

    @Test
    public void givenUserLevelAdvanced_whenGeneratingWorkout_thenSetHighIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.ADVANCED);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        WorkoutRequest workoutRequest = new WorkoutRequest();

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);
        this.validateUserLevelIntensity(5, 40, 20,
                8, 12, workoutProcessing);

        testSession.dispose();
    }


    /*************************
     *  WORKOUT TYPE INTENSITY TESTS
     **************************/

    @Test
    public void givenStrengthWorkout_whenGeneratingWorkout_thenSetSets() {

        // prep data
        UserInformation userInfo = new UserInformation();
        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        workoutProcessing.setNumOfExercises(5); // must be > 0 to trigger rule

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.STRENGTH);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);

        assertEquals(3, workoutProcessing.getSets());
        assertEquals(90, workoutProcessing.getRestBetweenSets());

        testSession.dispose();
    }

    @Test
    public void givenConditioningWorkout_whenGeneratingWorkout_thenSetRoundsAndIntervals() {

        // prep data
        UserInformation userInfo = new UserInformation();
        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        workoutProcessing.setNumOfExercises(5); // must be > 0 to trigger rule

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.CONDITIONING);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);

        assertEquals(8, workoutProcessing.getNumOfIntervals());
        assertEquals(4, workoutProcessing.getNumOfRounds());
        assertEquals(120, workoutProcessing.getRestBetweenRounds());

        testSession.dispose();
    }

    @Test
    public void givenComboWorkout_whenGeneratingWorkout_thenSetHighIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        WorkoutProcessing workoutProcessing = new WorkoutProcessing();
        workoutProcessing.setNumOfExercises(5);

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.COMBO);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(2, rulesFired);

        assertEquals(5, workoutProcessing.getSets());
        assertEquals(60, workoutProcessing.getRestBetweenSets());
        assertEquals(8, workoutProcessing.getNumOfIntervals());
        assertEquals(2, workoutProcessing.getNumOfRounds());
        assertEquals(120, workoutProcessing.getRestBetweenRounds());

        testSession.dispose();
    }


    /*************************
     *  MULTIPLE RULE TRIGGERING TESTS
     **************************/

    @Test
    public void givenUserLevelBeginnerAndStrengthWorkout_whenGeneratingWorkout_thenSetLowIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.BEGINNER);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.STRENGTH);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(3, rulesFired);
        this.validateUserLevelIntensity(3, 20, 10,
                4, 8, workoutProcessing);

        assertEquals(3, workoutProcessing.getSets());
        assertEquals(90, workoutProcessing.getRestBetweenSets());

        testSession.dispose();
    }

    @Test
    public void givenUserLevelIntermediateAndConditioningWorkout_whenGeneratingWorkout_thenSetMediumIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.INTERMEDIATE);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.CONDITIONING);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(3, rulesFired);
        this.validateUserLevelIntensity(4, 30, 15,
                6, 10, workoutProcessing);

        assertEquals(8, workoutProcessing.getNumOfIntervals());
        assertEquals(4, workoutProcessing.getNumOfRounds());
        assertEquals(120, workoutProcessing.getRestBetweenRounds());

        testSession.dispose();
    }

    @Test
    public void givenUserLevelAdvancedAndComboWorkout_whenGeneratingWorkout_thenSetHighIntensity() {

        // prep data
        UserInformation userInfo = new UserInformation();
        userInfo.setUserLevel(UserLevel.ADVANCED);

        WorkoutProcessing workoutProcessing = new WorkoutProcessing();

        WorkoutRequest workoutRequest = new WorkoutRequest();
        workoutRequest.setWorkoutType(ExerciseType.COMBO);

        // create session and trigger rules
        KieSession testSession = this.prepSessionWithFacts(userInfo, workoutProcessing, workoutRequest);
        int rulesFired = testSession.fireAllRules();

        // make assertions
        assertEquals(3, rulesFired);
        this.validateUserLevelIntensity(2, 40, 20,
                8, 12, workoutProcessing);

        assertEquals(5, workoutProcessing.getSets());
        assertEquals(60, workoutProcessing.getRestBetweenSets());
        assertEquals(8, workoutProcessing.getNumOfIntervals());
        assertEquals(2, workoutProcessing.getNumOfRounds());
        assertEquals(120, workoutProcessing.getRestBetweenRounds());

        testSession.dispose();
    }

    /*************************
     * ASSERTION UTILITY METHODS
     **************************/

    private void validateMuscleGroups(int lowerBound, int upperBound, WorkoutProcessing workoutProcessing) {
        for (int i = 0; i < workoutProcessing.getNextMuscleGroups().length; i++) {
            assertTrue(workoutProcessing.getNextMuscleGroups()[i] >= lowerBound);
            assertTrue(workoutProcessing.getNextMuscleGroups()[i] <= upperBound);
        }
    }

    private void validateUserLevelIntensity(int exerciseNum, int workInterval, int restInterval,
                                            int repsLowerBound, int repsUpperBound,
                                            WorkoutProcessing workoutProcessing) {

        assertEquals(exerciseNum, workoutProcessing.getNumOfExercises());
        assertEquals(workInterval, workoutProcessing.getWorkInterval());
        assertEquals(restInterval, workoutProcessing.getRestInterval());
        assertTrue(workoutProcessing.getReps() >= repsLowerBound);
        assertTrue(workoutProcessing.getReps() <= repsUpperBound);

    }

}
