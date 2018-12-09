import { MESSAGES } from './common.stubs';
import { Authentication } from './../login/common/auth.model';
import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';
import { Login } from '../login/common/login.model';
import { BehaviorSubject } from 'rxjs';
import { Message } from '../common/common.model';

const REFRESH_TOKEN = 'refresh_token';
const ACCESS_TOKEN = 'access_token';

export const LOGIN: Login = {
    username: 'testUser',
    password: 'test'
  };

export const AUTH: Authentication = {
  access_token: ACCESS_TOKEN,
  refresh_token: REFRESH_TOKEN
 };


@Injectable()
export class LoginServiceStub {

  private messageSource = new BehaviorSubject(new User());
  user = this.messageSource.asObservable();

  login(login: Login): Promise<Authentication> {
    return Promise.resolve(AUTH);
  }

  saveTokensInSession(auth: Authentication) {
    sessionStorage.setItem(ACCESS_TOKEN, auth.access_token);
    sessionStorage.setItem(REFRESH_TOKEN, auth.refresh_token);
  }

  clearAccessToken() {
    sessionStorage.removeItem(ACCESS_TOKEN);
  }

  clearRefreshToken() {
    sessionStorage.removeItem(REFRESH_TOKEN);
  }

  updateUser(user: User) {
    this.messageSource.next(user);
  }

  logoutAccessToken(): Promise<Message> {
    return Promise.resolve(MESSAGES.get(ACCESS_TOKEN));
  }

  logoutRefreshToken(): Promise<Message> {
    return Promise.resolve(MESSAGES.get(REFRESH_TOKEN));
  }
}
