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
        this.loginService.updateUser(this.user);
      });
  }

  logoutAction() {
    this.loginService.logoutAccessToken();
    this.loginService.clearAccessToken();
    this.loginService.logoutRefreshToken();
    this.loginService.clearRefreshToken();
    this.user = undefined;
    this.auth = undefined;
    this.login.username = undefined;
    this.login.password = undefined;
    this.loginService.updateUser(new User());
    this.router.navigate(['/']);
  }

}
