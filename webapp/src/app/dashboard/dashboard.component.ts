import { LoginService } from './../login/common/login.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../users/common/user.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user: User;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
    this.loginService.getUser().subscribe(user => {
      this.user = user;
    });
  }

  getMessage() {
    const message = 'Welcome!';
    if (this.user.username !== undefined) {
      return 'Welcome ' + this.user.username + '!';
    }
    return message;
  }

}
