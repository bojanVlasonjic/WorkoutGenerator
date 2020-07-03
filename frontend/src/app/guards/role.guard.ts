import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { ToasterService } from '../services/toaster.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private router: Router,
    private authSvc: AuthenticationService
    ) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    
    const loggedUser = this.authSvc.getCurrentUser();
    let permissions = route.data.permissions as Array<string>;

    if (loggedUser == null || !permissions.includes(loggedUser.role)) {
      this.router.navigate(['/home']);
      return false;
    }

    return true;
  }
}