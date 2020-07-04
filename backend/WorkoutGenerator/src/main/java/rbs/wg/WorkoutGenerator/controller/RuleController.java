package rbs.wg.WorkoutGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbs.wg.WorkoutGenerator.dto.RuleDto;
import rbs.wg.WorkoutGenerator.service.RuleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rule")
public class RuleController {

    @Autowired
    RuleService ruleService;

    @Value("${drools.templates.simple.path}")
    private String simpleTemplatePath;



    @GetMapping("/simple-template")
    public ResponseEntity<String> getSimpleTemplate() {
        return ResponseEntity.ok(ruleService.getSimpleTemplate(simpleTemplatePath));
    }

    @GetMapping
    public ResponseEntity<List<RuleDto>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @PostMapping
    public ResponseEntity<RuleDto> createRule(@Valid @RequestBody RuleDto ruleDto) {
        return new ResponseEntity<>(ruleService.createRule(ruleDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRule(@PathVariable Long id) {

        return ResponseEntity.ok(ruleService.deleteRule(id));
    }
}
