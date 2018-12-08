import { Login } from './login.model';
import { Injectable } from '@angular/core';
import { BaseApiService } from 'src/app/common/base.service';
import { Http } from '@angular/http';
import { Authentication } from './auth.model';
import { User } from 'src/app/users/common/user.model';
import { Observable, Observer, BehaviorSubject } from 'rxjs';

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

  saveTokensInSession(auth: Authentication) {
    sessionStorage.setItem('access_token', auth.access_token);
    sessionStorage.setItem('refresh_token', auth.refresh_token);
  }

  clearTokensInSession() {
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('refresh_token');
  }

  updateUser(user: User) {
    console.log('loginService: ' + JSON.stringify(user));
    this.messageSource.next(user);
  }
}
