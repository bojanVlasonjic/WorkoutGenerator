import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HeartRateDto } from 'src/app/dtos/heart-rate.dto';
import { Chart } from 'chart.js';
import { Subscription, timer } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { HeartRateService } from 'src/app/services/heart-rate.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ToasterService } from 'src/app/services/toaster.service';


@Component({
  selector: 'app-hr-simulation',
  templateUrl: './hr-simulation.component.html',
  styleUrls: ['./hr-simulation.component.css']
})
export class HrSimulationComponent implements OnInit, OnDestroy {

  age: number;
  morningPulse: number;
  heartRateDto: HeartRateDto;

  chart: any;
  chartOptions: any;

  subscription: Subscription;
  simulationStarted: boolean;

  @ViewChild('sf', {static: false}) simulationForm: any;

  constructor(
    private heartRateService: HeartRateService,
    private authService: AuthenticationService,
    private toasterService: ToasterService
    ) { 
    this.heartRateDto = new HeartRateDto(this.authService.getCurrentUser().email);
    this.simulationStarted = false;
    this.setDefaultHeartRates();
  }

  ngOnInit() {

    this.heartRateService.startSimulation(this.authService.getCurrentUser().email).subscribe();

    this.chart = new Chart('canvas', {
      type: 'bar',
      options: {
        responsive: true,
        title: {
          display: true,
          text: 'Heart rate monitor'
        },
        scales: {
          yAxes: [{
            ticks: {
              min: 0,
              max: 250,
              stepSize: 50
            }
          }]
        }
      },
      data: {
        labels: ['My heart rate', 'Goal'],
        datasets: [
          {
            type: 'bar',
            label: 'Heart beats per minute',
            data: [0, 0],
            backgroundColor: "#333",
            fill: false
          },
        ]
      }
    });
   
  }

  calculateRequiredPulse(): void {

    if (!this.simulationForm.valid) {
      return;
    }

    this.heartRateDto.goal = Math.round((220 - this.age - this.morningPulse) * 0.8 + this.morningPulse);
    this.updateChart();
    this.startSimulation();
  } 

  updateChart() {
    this.chart.data.datasets[0].data = [this.heartRateDto.heartRate, this.heartRateDto.goal];
    this.chart.update();
  }

  increaseHeartRate(): void {
    if(this.heartRateDto.heartRate < 250) {
      this.heartRateDto.heartRate += 1;
    }
  }

  decreaseHeartRate(): void {
    if(this.heartRateDto.heartRate > 50) {
      this.heartRateDto.heartRate -= 1;
    }
  }

  startSimulation(): void {
    this.simulationStarted = true;

    this.subscription = timer(0, 5000).pipe(
      switchMap(() => this.heartRateService.sendHeartRate(this.heartRateDto))
    ).subscribe(
      result => { 
        this.heartRateDto = result; 
        this.updateChart();
      },
      err => { this.toasterService.showErrorMessage(err); }
    );

  }

  endSimulation(): void {
    this.simulationStarted = false;
    if(this.subscription != null) {
      this.subscription.unsubscribe();
    }
    this.setDefaultHeartRates();
  }

  setDefaultHeartRates(): void {
    this.heartRateDto.heartRate = 60;
    this.heartRateDto.goal = 60;
  }

  ngOnDestroy() {
    this.endSimulation();
    this.heartRateService.stopSimulation(this.authService.getCurrentUser().email).subscribe();
  }

}
