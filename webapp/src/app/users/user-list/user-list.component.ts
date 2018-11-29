import { UserService } from './../common/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../common/user.model';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers()
      .then(users => {
        this.users = users;
      });
  }

  isUsersEmpty(): boolean {
    return this.users == null || this.users.length === 0;
  }

}
