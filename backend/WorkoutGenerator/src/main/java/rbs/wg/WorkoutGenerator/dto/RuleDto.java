package rbs.wg.WorkoutGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.model.Rule;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class RuleDto {

    private Long id;

    private String ruleName;

    @NotEmpty(message = "Rule content is required")
    private String ruleContent;

    public RuleDto(Rule rule) {
        this.id = rule.getId();
        this.ruleName = rule.getName();
        this.ruleContent = rule.getContent();
    }
}
