import { Authentication } from './../login/common/auth.model';
import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';
import { Login } from '../login/common/login.model';

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

  login(login: Login): Promise<Authentication> {
    return Promise.resolve(AUTH);
  }
}
