import { LoginService } from './../login/common/login.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../users/common/user.model';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  user: User;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
    this.loginService.user.subscribe(user => this.user = user);
  }

}
