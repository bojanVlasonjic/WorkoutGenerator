package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.HeartRateDto;
import rbs.wg.WorkoutGenerator.service.HeartRateService;

@RestController
@RequestMapping("api/heart-rate")
public class HeartRateController {


    @Autowired
    HeartRateService heartRateService;

    @GetMapping("/start/{userId}")
    public ResponseEntity<Boolean> startSimulation(@PathVariable Long userId) {

        return new ResponseEntity<>(heartRateService.startSimulation(userId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HeartRateDto> sendHeartRate(@RequestBody HeartRateDto heartRateDto) {

        return new ResponseEntity<>(heartRateService.sendHeartRate(heartRateDto), HttpStatus.ACCEPTED);

    }

    @GetMapping("/stop/{userId}")
    public ResponseEntity<Boolean> stopSimulation(@PathVariable Long userId) {

        return new ResponseEntity<>(heartRateService.stopSimulation(userId), HttpStatus.OK);
    }


}
