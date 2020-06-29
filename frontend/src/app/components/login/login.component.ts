import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthenticationRequestDto } from 'src/app/dtos/authentication-request.dto';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ToasterService } from 'src/app/services/toaster.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authRequest: AuthenticationRequestDto;
  @ViewChild('lf', {static: false}) loginForm: any;

  constructor(
    private authService: AuthenticationService,
    private toasterService: ToasterService,
    private router: Router
    ) {
    this.authRequest = new AuthenticationRequestDto();
  }

  ngOnInit() {
  }

  login(): void {

    if(!this.loginForm.valid) {
      return;
    }

    this.authService.authenticate(this.authRequest).subscribe(
      data => {
        this.authService.rememberUser(data);
        this.router.navigate(['/home']);
      },
      err => {
        this.toasterService.showErrorMessage(err);
      } 
    );

  }

}
