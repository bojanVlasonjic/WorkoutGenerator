import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from 'src/app/dtos/user.dto';
import { UserService } from 'src/app/services/user.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {

  userData: UserDto;
  confirmedPassword: string;

  isEditing: boolean;
  passwordsMatch: boolean;
  submittingRequest: boolean;

  @ViewChild("rf", {static: false}) registerForm: any;

  constructor(
    private authService: AuthenticationService,
    private userService: UserService,
    private toasterService: ToasterService,
    private router: Router
    ) { 
    this.userData = new UserDto();
    this.passwordsMatch = false;
    this.submittingRequest = false;
  }

  ngOnInit() {

    if(this.router.url === '/profile') {
      this.isEditing = true;
      this.loadUser(this.authService.getCurrentUser().email);
    } else {
      this.isEditing = false;
    }

  }

  loadUser(email: string): void {

    this.userService.getUserByEmail(email).subscribe(
      data => {
        this.userData = data;
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );

  }

  manageUser(): void {
    
    if(!this.registerForm.valid || !this.passwordsMatch) {
      return;
    }

    this.submittingRequest = true;
    if(this.isEditing) {
      this.updateProfile();
    } else {
      this.register();
    }

  }

  register(): void {
    this.userService.createNewUser(this.userData).subscribe(
      data => {
        this.userData = data;
        this.registerForm.reset();
        this.router.navigate(['/home']);
        this.toasterService.showMessage('Success', 'Registration successful');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    ).add(
      () => { this.submittingRequest = false; }
    );
  }

  updateProfile(): void {

    this.userService.updateUser(this.userData).subscribe(
      data => {
        this.userData = data;
        this.toasterService.showMessage('Success', 'Profile successfully updated');
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    ).add(
      () => { this.submittingRequest = false; }
    );

  }

  checkPasswordMatch() {
    this.passwordsMatch = this.confirmedPassword === this.userData.password;
  }


}
