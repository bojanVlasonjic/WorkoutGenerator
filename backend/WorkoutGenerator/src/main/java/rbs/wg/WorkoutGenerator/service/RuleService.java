package rbs.wg.WorkoutGenerator.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbs.wg.WorkoutGenerator.dto.RuleDto;
import rbs.wg.WorkoutGenerator.exception.ApiBadRequestException;
import rbs.wg.WorkoutGenerator.exception.ApiNotFoundException;
import rbs.wg.WorkoutGenerator.model.Rule;
import rbs.wg.WorkoutGenerator.repository.RuleRepository;
import rbs.wg.WorkoutGenerator.util.KieSessionDynamic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private KieSessionDynamic kieSessionDynamic;


    public List<RuleDto> getAllRules() {

        return this.ruleRepository
                .findAll()
                .stream()
                .map(RuleDto::new)
                .collect(Collectors.toList());
    }

    public RuleDto createRule(RuleDto ruleDto) {

        ruleDto.setRuleName(this.extractRuleName(ruleDto.getRuleContent()));

        if(ruleRepository.findByName(ruleDto.getRuleName()).isPresent()) {
            throw new ApiBadRequestException("Rule name already exists");
        }

        Rule rule = new Rule(ruleDto);
        String drl = kieSessionDynamic.combinePreviousRules(rule.getContent());
        kieSessionDynamic.createSessionFromDrl(drl);

        try {
            ruleRepository.save(rule);
        } catch(Exception ex) {
            throw new ApiBadRequestException("Failed to save rule. It may be too long, or you have syntax errors.");
        }

        return ruleDto;
    }

    public String getSimpleTemplate(String templatePath) {
        File file = new File(templatePath);
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ApiBadRequestException("Failed to load template");
        }
    }


    public Boolean deleteRule(Long ruleId) {

        Rule rule = this.ruleRepository
                .findById(ruleId)
                .orElseThrow(() -> new ApiNotFoundException("Rule not found"));

        try {
            ruleRepository.delete(rule);
        } catch (Exception ex) {
            throw new ApiBadRequestException("Failed to delete rule. Please refresh and try again");
        }

        kieSessionDynamic.createSessionFromOldRules();

        return true;
    }


    private String extractRuleName(String ruleContent) {

        int ruleStart = ruleContent.indexOf("rule");
        int ruleNameStart = ruleContent.indexOf("\"", ruleStart);
        int ruleNameEnd = ruleContent.indexOf("\"", ruleNameStart+1);

        try {
            return ruleContent.substring(ruleNameStart+1, ruleNameEnd).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new ApiBadRequestException("Failed to extract rule name");
        }

    }
}
