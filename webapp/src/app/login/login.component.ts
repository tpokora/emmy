import { UserService } from './../users/common/user.service';
import { Authentication } from './common/auth.model';
import { LoginService } from './common/login.service';
import { Component, OnInit } from '@angular/core';
import { Login } from './common/login.model';
import { Router } from '@angular/router';
import { User } from '../users/common/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login;
  auth: Authentication;
  user: User;

  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.login = new Login();
  }

  loginAction() {
    console.log(JSON.stringify(this.login));
    this.loginService.login(this.login)
      .then(auth => {
        this.auth = auth;
        this.loginService.saveTokensInSession(this.auth);
        this.updateUser(this.login.username);
        this.router.navigate(['/']);
      });
  }

  private updateUser(username: string) {
    this.userService.getUser(username)
      .then(user => {
        this.user = user;
        console.log('loginComponent: ' + JSON.stringify(this.user));
        this.loginService.updateUser(this.user);
      });
  }

  logoutAction() {

  }

}
