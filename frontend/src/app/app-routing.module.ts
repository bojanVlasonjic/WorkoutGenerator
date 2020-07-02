import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { GenerateWorkoutComponent } from './components/generate-workout/generate-workout.component';
import { MyWorkoutsComponent } from './components/my-workouts/my-workouts.component';
import { HrSimulationComponent } from './components/hr-simulation/hr-simulation.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'generate-workout',
    component: GenerateWorkoutComponent
  },
  {
    path: 'my-workouts',
    component: MyWorkoutsComponent
  },
  {
    path: 'hr-simulation',
    component: HrSimulationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
