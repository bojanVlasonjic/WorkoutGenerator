import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RuleDto } from '../dtos/rule.dto';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  constructor(private http: HttpClient) { }

  getSimpleTemplate(): Observable<any> {
    return this.http.get('api/rule/simple-template', { responseType: 'text' });
  }

  getAllRules(): Observable<any> {
    return this.http.get('api/rule');
  }

  createRule(ruleData: RuleDto):  Observable<any> {
    return this.http.post('api/rule', ruleData);
  }
}
