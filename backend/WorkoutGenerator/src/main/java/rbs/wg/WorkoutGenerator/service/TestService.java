package rbs.wg.WorkoutGenerator.service;

import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.TestMessage;

@Service
public class TestService {

    public void printMessage(TestMessage message) {
        System.out.println(message.getMessage());
    }
}
