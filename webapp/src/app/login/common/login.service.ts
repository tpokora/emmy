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
    const url = `${this.url}/auth-jwt/`;
    return this.http.post(url, JSON.stringify(login), { headers: this.generateHeaders() })
      .toPromise()
      .then(response => response.json() as Authentication)
      .catch(this.handleError);
  }

  logout() {
    this.clearAccessToken();
    this.clearRefreshToken();
    this.clearToken();
    this.updateUser(new User());
  }

  logoutAccessToken(): Promise<Message> {
    const url = `${this.url}/logout/access`;
    const message = this.http.post(url, '', { headers: this.generateHeadersWithToken(true) })
      .toPromise()
      .then(response => response.json() as Message)
      .catch(this.handleError);

    return message;
  }

  logoutRefreshToken(): Promise<Message> {
    const url = `${this.url}/logout/refresh`;
    const message = this.http.post(url, '', { headers: this.generateHeadersWithToken(false) })
      .toPromise()
      .then(response => response.json() as Message)
      .catch(this.handleError);

    return message;
  }

  saveTokensInSession(auth: Authentication) {
    if (auth.access_token !== undefined) {
      sessionStorage.setItem(this.ACCESS_TOKEN, auth.access_token);
    }
    if (auth.refresh_token !== undefined) {
      sessionStorage.setItem(this.REFRESH_TOKEN, auth.refresh_token);
    }
    if (auth.token !== undefined) {
      sessionStorage.setItem(this.TOKEN, auth.token);
    }
  }

  clearAccessToken() {
    sessionStorage.removeItem(this.ACCESS_TOKEN);
  }

  clearRefreshToken() {
    sessionStorage.removeItem(this.REFRESH_TOKEN);
  }

  clearToken() {
    sessionStorage.removeItem(this.TOKEN);
  }

  updateUser(user: User) {
    this.messageSource.next(user);
  }
}
