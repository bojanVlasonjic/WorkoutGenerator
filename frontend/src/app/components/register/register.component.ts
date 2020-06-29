import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from 'src/app/dtos/user.dto';
import { UserService } from 'src/app/services/user.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userData: UserDto;
  confirmedPassword: string;
  @ViewChild("rf", {static: false}) registerForm: any;

  constructor(
    private userService: UserService,
    private toasterService: ToasterService,
    private router: Router
    ) { 
    this.userData = new UserDto();
  }

  ngOnInit() {
  }

  register(): void {
    
    if(!this.registerForm.valid) {
      return;
    }

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
    );

  }

}
