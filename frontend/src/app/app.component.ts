import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Workout Generator';
  isNavBarCollapsed: boolean = false;

  userRole: string = environment.roleUser;
  adminRole: string = environment.roleAdmin;

  constructor(
    private authService: AuthenticationService,
    private router: Router
    ) { }

  get isSignedIn(): boolean {
    return this.authService.isUserSignedIn();
  }

  getUserRole(): string {
    if(this.authService.isUserSignedIn()) {
      return this.authService.getCurrentUser().role;
    }
  }

  logout(): void {
    this.authService.clearUser();
    this.router.navigate(['/home']);
  }
}
