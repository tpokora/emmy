import { Authentication } from './../login/common/auth.model';
import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';
import { Login } from '../login/common/login.model';
import { BehaviorSubject } from 'rxjs';

export const LOGIN: Login = {
    username: 'testUser',
    password: 'test'
  };

  export const AUTH: Authentication = {
    refresh_token: 'refresh_token',
    access_token: 'access_token'
  };

@Injectable()
export class LoginServiceStub {

  private messageSource = new BehaviorSubject(new User());
  user = this.messageSource.asObservable();

  login(login: Login): Promise<Authentication> {
    return Promise.resolve(AUTH);
  }
}
