package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.RuleDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 2500)
    private String content;

    public Rule(RuleDto ruleDto) {
        this.name = ruleDto.getRuleName();
        this.content = ruleDto.getRuleContent();
    }
}
