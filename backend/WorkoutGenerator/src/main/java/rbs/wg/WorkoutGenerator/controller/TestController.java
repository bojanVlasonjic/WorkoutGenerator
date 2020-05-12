package rbs.wg.WorkoutGenerator.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rbs.wg.WorkoutGenerator.dto.TestMessage;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    KieSession workoutSession;

    @PostMapping("/{message}")
    public TestMessage createMessage(@PathVariable String message) {

        TestMessage testMessage = new TestMessage(0, message);
        workoutSession.insert(testMessage);
        workoutSession.fireAllRules();
        return testMessage;

    }
}
