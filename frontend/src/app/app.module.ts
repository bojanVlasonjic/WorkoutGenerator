import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { GenerateWorkoutComponent } from './components/generate-workout/generate-workout.component';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { MyWorkoutsComponent } from './components/my-workouts/my-workouts.component';
import { WorkoutPreviewComponent } from './components/workout-preview/workout-preview.component';
import { ExercisePreviewComponent } from './components/exercise-preview/exercise-preview.component';
import { HrSimulationComponent } from './components/hr-simulation/hr-simulation.component';
import { ManageExerciseComponent } from './components/manage-exercise/manage-exercise.component';
import { ViewExercisesComponent } from './components/view-exercises/view-exercises.component';
import { ViewUsersComponent } from './components/view-users/view-users.component';
import { ManageUserComponent } from './components/manage-user/manage-user.component';
import { AddReviewComponent } from './components/add-review/add-review.component';
import { AceEditorModule } from 'ng2-ace-editor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ManageUserComponent,
    GenerateWorkoutComponent,
    MyWorkoutsComponent,
    WorkoutPreviewComponent,
    ExercisePreviewComponent,
    HrSimulationComponent,
    ManageExerciseComponent,
    ViewExercisesComponent,
    ViewUsersComponent,
    AddReviewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    AceEditorModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
