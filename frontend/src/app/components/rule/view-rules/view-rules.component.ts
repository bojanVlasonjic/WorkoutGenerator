import { Component, OnInit } from '@angular/core';
import { RuleDto } from 'src/app/dtos/rule.dto';
import { RuleService } from 'src/app/services/rule.service';
import { ToasterService } from 'src/app/services/toaster.service';

@Component({
  selector: 'app-view-rules',
  templateUrl: './view-rules.component.html',
  styleUrls: ['./view-rules.component.css']
})
export class ViewRulesComponent implements OnInit {

  private rules: Array<RuleDto>;
  waitingForRequest: boolean;

  constructor(
    private ruleService: RuleService,
    private toasterSvc: ToasterService
    ) {
    this.rules = new Array<RuleDto>();
  }

  ngOnInit() {
    this.waitingForRequest = true;
    this.getAllRules();
  }

  getAllRules(): void {
    
    this.ruleService.getAllRules().subscribe(
      data => {
        this.rules = data;
      },
      err => {
        this.toasterSvc.showErrorMessage(err);
      }
    ).add(
      () => {
        this.waitingForRequest = false;
      }
    );
  }

  removeRule(rule: RuleDto) {

    if (!window.confirm(`Are you sure you want to delete rule '${rule.ruleName}'?`)) {
      return;
    }

    let ruleIndex = this.rules.indexOf(rule);

    this.ruleService.deleteRule(rule.id).subscribe(
      data => {
        if (ruleIndex > -1) {
          this.rules.splice(ruleIndex, 1);
        }
      },
      err => {
        this.toasterSvc.showErrorMessage(err);
      }
    );

  }

}
