import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Workout Generator';
  isNavBarCollapsed: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router
    ) { }

  get isSignedIn(): boolean {
    return this.authService.isUserSignedIn();
  }

  logout(): void {
    this.authService.clearUser();
    this.router.navigate(['/home']);
  }
}
