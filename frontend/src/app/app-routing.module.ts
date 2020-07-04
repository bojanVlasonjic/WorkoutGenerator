import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { GenerateWorkoutComponent } from './components/generate-workout/generate-workout.component';
import { MyWorkoutsComponent } from './components/my-workouts/my-workouts.component';
import { HrSimulationComponent } from './components/hr-simulation/hr-simulation.component';
import { ViewExercisesComponent } from './components/view-exercises/view-exercises.component';
import { ManageExerciseComponent } from './components/manage-exercise/manage-exercise.component';
import { ViewUsersComponent } from './components/view-users/view-users.component';
import { ManageUserComponent } from './components/manage-user/manage-user.component';
import { LoginGuard } from './guards/login.guard';
import { RoleGuard } from './guards/role.guard';
import { environment } from 'src/environments/environment';
import { RuleComponent } from './components/rule/rule.component';
import { ViewRulesComponent } from './components/rule/view-rules/view-rules.component';
import { NewRuleComponent } from './components/rule/new-rule/new-rule.component';


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
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'register',
    component: ManageUserComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'profile',
    component: ManageUserComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleUser]}
  },
  {
    path: 'generate-workout',
    component: GenerateWorkoutComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleUser]}
  },
  {
    path: 'my-workouts',
    component: MyWorkoutsComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleUser]}
  },
  {
    path: 'hr-simulation',
    component: HrSimulationComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleUser]}
  },
  {
    path: 'view-exercises',
    component: ViewExercisesComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleAdmin]}
  },
  {
    path: 'manage-exercise',
    component: ManageExerciseComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleAdmin]}
  },
  {
    path: 'manage-exercise/:id',
    component: ManageExerciseComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleAdmin]}
  },
  {
    path: 'view-users',
    component: ViewUsersComponent,
    canActivate: [RoleGuard],
    data: { permissions: [environment.roleAdmin]}
  },
  {
    path: 'rules',
    component: RuleComponent,
    canActivate: [RoleGuard],
    data: {permissions: [environment.roleAdmin]},
    children: [
      {
        path: '',
        redirectTo: 'view',
        pathMatch: 'full'
      },
      {
        path: 'view',
        component: ViewRulesComponent
      },
      {
        path: 'new',
        component: NewRuleComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
