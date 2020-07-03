package rbs.wg.WorkoutGenerator.rules;

import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rbs.wg.WorkoutGenerator.facts.UserInformation;
import rbs.wg.WorkoutGenerator.model.Equipment;
import rbs.wg.WorkoutGenerator.model.Exercise;
import rbs.wg.WorkoutGenerator.model.StrengthRegime;
import rbs.wg.WorkoutGenerator.model.UserLevel;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WorkoutServiceTests {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private Random random;

    private int userWeight = 70; // in kg

    private KieSession prepSessionWithFacts(UserInformation userInformation,
                                            StrengthRegime strengthRegime) {

        KieSession testSession = kieContainer.newKieSession("WGTestSession");
        testSession.setGlobal("random", random);

        testSession.insert(userInformation);
        testSession.insert(strengthRegime);

        testSession.getAgenda().getAgendaGroup("workLoad").setFocus();

        return testSession;
    }

    private UserInformation prepUser(UserLevel userLevel) {

        UserInformation userInformation = new UserInformation();
        userInformation.setUserLevel(userLevel);
        userInformation.setWeight(this.userWeight);

        return userInformation;
    }

    private StrengthRegime prepExercise(Equipment equipment) {

        Exercise exercise = new Exercise();
        exercise.setEquipment(equipment);

        StrengthRegime strengthRegime = new StrengthRegime();
        strengthRegime.setExercise(exercise);

        return strengthRegime;
    }

    private void printResults(String methodName, double actualValue, double lowerBound, double upperBound) {
        System.out.println("\n\n\n");
        System.out.println(methodName);
        System.out.println("Actual workload: " + actualValue);

        double lowerValueWithTolerance = lowerBound * this.userWeight - 1;
        System.out.println("Expected workload between " + lowerValueWithTolerance + " and " +  upperBound * this.userWeight);
    }


    /************************* ****************
     *  EXERCISE WORK LOAD TESTS WITH BARBELL
     ************************* ****************/

    @Test
    public void givenUserBeginner_whenExerciseEquipmentBarbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.BEGINNER);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.BARBELL_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserBeginner_whenExerciseEquipmentBarbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.35, 0.5);

        // workLoad should be between 35% and 50% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.35 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.5 * this.userWeight);
    }

    @Test
    public void givenUserIntermediate_whenExerciseEquipmentBarbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.INTERMEDIATE);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.BARBELL_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserIntermediate_whenExerciseEquipmentBarbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.45, 0.65);

        // workLoad should be between 45% and 65% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.45 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.65 * this.userWeight);

    }

    @Test
    public void givenUserAdvanced_whenExerciseEquipmentBarbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.ADVANCED);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.BARBELL_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserAdvanced_whenExerciseEquipmentBarbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.55, 0.70);

        // workLoad should be between 55% and 70% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.55 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.70 * this.userWeight);

    }


    /************************* ****************
     *  EXERCISE WORK LOAD TESTS WITH DUMBBELLS
     ************************** ***************/
    @Test
    public void givenUserBeginner_whenExerciseEquipmentDumbbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.BEGINNER);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.DUMBBELLS_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserBeginner_whenExerciseEquipmentDumbbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.08, 0.12);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.08 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.12 * this.userWeight);

    }

    @Test
    public void givenUserIntermediate_whenExerciseEquipmentDumbbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.INTERMEDIATE);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.DUMBBELLS_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserIntermediate_whenExerciseEquipmentDumbbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.10, 0.15);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.10 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.15 * this.userWeight);

    }


    @Test
    public void givenUserAdvanced_whenExerciseEquipmentDumbbell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.ADVANCED);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.DUMBBELLS_WITH_PLATES);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserAdvanced_whenExerciseEquipmentDumbbell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.15, 0.20);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.15 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.20 * this.userWeight);

    }


    /************************* ****************
     *  EXERCISE WORK LOAD TESTS WITH KETTLEBELLS
     ************************** ***************/
    @Test
    public void givenUserBeginner_whenExerciseEquipmentKettlebell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.BEGINNER);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.KETTLEBELL);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserBeginner_whenExerciseEquipmentKettlebell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.15, 0.20);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.15 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.20 * this.userWeight);

    }

    @Test
    public void givenUserIntermediate_whenExerciseEquipmentKettlebell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.INTERMEDIATE);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.KETTLEBELL);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserIntermediate_whenExerciseEquipmentKettlebell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.22, 0.28);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.22 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.28 * this.userWeight);

    }

    @Test
    public void givenUserAdvanced_whenExerciseEquipmentKettlebell_setWorkLoad() {

        UserInformation userInformation = this.prepUser(UserLevel.ADVANCED);
        StrengthRegime strengthRegime = this.prepExercise(Equipment.KETTLEBELL);

        KieSession testSession = this.prepSessionWithFacts(userInformation, strengthRegime);
        int rulesFired = testSession.fireAllRules();
        assertEquals(1, rulesFired);

        printResults("givenUserAdvanced_whenExerciseEquipmentKettlebell_setWorkLoad",
                strengthRegime.getWorkLoad(), 0.24, 0.32);

        // workLoad should be between 8% and 12% of user weight
        assertTrue(strengthRegime.getWorkLoad() >= 0.24 * this.userWeight - 1); // -1 for rounding tolerance
        assertTrue(strengthRegime.getWorkLoad() <= 0.32 * this.userWeight);

    }

}
