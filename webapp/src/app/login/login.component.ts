import { Authentication } from './common/auth.model';
import { LoginService } from './common/login.service';
import { Component, OnInit } from '@angular/core';
import { Login } from './common/login.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login;
  auth: Authentication;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.login = new Login();
  }

  loginAction() {
    console.log(JSON.stringify(this.login));
    this.loginService.login(this.login)
      .then(auth => {
        this.auth = auth ;
        this.loginService.saveTokensInSession(this.auth);
        this.router.navigate(['/']);
      });
  }

  logoutAction() {
    
  }

}
