package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.HeartRateDto;
import rbs.wg.WorkoutGenerator.service.HeartRateService;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("api/heart-rate")
public class HeartRateController {


    @Autowired
    HeartRateService heartRateService;

    @GetMapping("/start/{userEmail}")
    public ResponseEntity<Boolean> startSimulation(@PathVariable String userEmail) {

        return new ResponseEntity<>(heartRateService.startSimulation(userEmail), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HeartRateDto> sendHeartRate(@RequestBody HeartRateDto heartRateDto) {

        return new ResponseEntity<>(heartRateService.sendHeartRate(heartRateDto), HttpStatus.ACCEPTED);

    }

    @GetMapping("/stop/{userEmail}")
    public ResponseEntity<Boolean> stopSimulation(@PathVariable String userEmail) {

        return new ResponseEntity<>(heartRateService.stopSimulation(userEmail), HttpStatus.OK);
    }


}
