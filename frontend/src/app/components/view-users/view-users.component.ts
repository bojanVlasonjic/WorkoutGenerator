import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/dtos/user.dto';
import { UserService } from 'src/app/services/user.service';
import { ToasterService } from 'src/app/services/toaster.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  users: Array<UserDto>;

  constructor(
    private userService: UserService,
    private toasterService: ToasterService
    ) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      data => {
        this.users = data;
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );
  }

  changeUserStatus(user: UserDto) {

    let userIndex = this.users.indexOf(user);
    this.userService.changeUserStatus(user.id).subscribe(
      data => {
        if (userIndex > -1) {
          this.users[userIndex] = data;
        }
      },
      err => {
        this.toasterService.showErrorMessage(err);
      }
    );

  }

}
