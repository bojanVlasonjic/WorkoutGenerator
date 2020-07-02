import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { GenerateWorkoutComponent } from './components/generate-workout/generate-workout.component';
import { MyWorkoutsComponent } from './components/my-workouts/my-workouts.component';
import { HrSimulationComponent } from './components/hr-simulation/hr-simulation.component';
import { ViewExercisesComponent } from './components/view-exercises/view-exercises.component';
import { ManageExerciseComponent } from './components/manage-exercise/manage-exercise.component';
import { ViewUsersComponent } from './components/view-users/view-users.component';


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
  },
  {
    path: 'view-exercises',
    component: ViewExercisesComponent
  },
  {
    path: 'manage-exercise',
    component: ManageExerciseComponent
  },
  {
    path: 'manage-exercise/:id',
    component: ManageExerciseComponent
  },
  {
    path: 'view-users',
    component: ViewUsersComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
