import { Login } from './login.model';
import { Injectable } from '@angular/core';
import { BaseApiService } from 'src/app/common/base.service';
import { Http } from '@angular/http';
import { Authentication } from './auth.model';
import { User } from 'src/app/users/common/user.model';
import { Observable, Observer, BehaviorSubject } from 'rxjs';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';

@Injectable()
export class LoginService extends BaseApiService {

  private messageSource = new BehaviorSubject(new User());
  user = this.messageSource.asObservable();

  constructor(private http: Http) {
    super();
  }

  login(login: Login): Promise<Authentication> {
    const url = `${this.url}/login`;
    return this.http.post(url, JSON.stringify(login), { headers: this.generateHeaders() })
      .toPromise()
      .then(response => response.json() as Authentication)
      .catch(this.handleError);
  }

  logoutAccessToken(): Promise<Message> {
    const url = `${this.url}/logout/access`;
    const message = this.http.post(url, '', { headers: this.generateHeadersWithToken(true) })
      .toPromise()
      .then(response => response.json() as Message)
      .catch(this.handleError);

    console.log('logoutAccess: ' + JSON.stringify(message));

    return message;
  }

  logoutRefreshToken(): Promise<Message> {
    const url = `${this.url}/logout/refresh`;
    const message = this.http.post(url, '', { headers: this.generateHeadersWithToken(false) })
      .toPromise()
      .then(response => response.json() as Message)
      .catch(this.handleError);

    console.log('logoutRefresh: ' + JSON.stringify(message));

    return message;
  }

  saveTokensInSession(auth: Authentication) {
    sessionStorage.setItem(this.ACCESS_TOKEN, auth.access_token);
    sessionStorage.setItem(this.REFRESH_TOKEN, auth.refresh_token);
  }

  clearAccessToken() {
    sessionStorage.removeItem(this.ACCESS_TOKEN);
  }

  clearRefreshToken() {
    sessionStorage.removeItem(this.REFRESH_TOKEN);
  }

  updateUser(user: User) {
    console.log('loginService: ' + JSON.stringify(user));
    this.messageSource.next(user);
  }
}