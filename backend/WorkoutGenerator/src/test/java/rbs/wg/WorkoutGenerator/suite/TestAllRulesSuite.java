package rbs.wg.WorkoutGenerator.suite;

import rbs.wg.WorkoutGenerator.cep.HeartRateServiceTests;
import rbs.wg.WorkoutGenerator.rules.ReviewAdjustingTests;
import rbs.wg.WorkoutGenerator.rules.WorkoutRequestServiceTests;
import rbs.wg.WorkoutGenerator.rules.WorkoutServiceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value=Suite.class)
@Suite.SuiteClasses({
        ReviewAdjustingTests.class,
        WorkoutRequestServiceTests.class,
        WorkoutServiceTests.class,
        HeartRateServiceTests.class
})
public class TestAllRulesSuite {
}
