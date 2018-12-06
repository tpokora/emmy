import { Login } from './login.model';
import { Injectable } from '@angular/core';
import { BaseApiService } from 'src/app/common/base.service';
import { Http } from '@angular/http';
import { Authentication } from './auth.model';

@Injectable()
export class LoginService extends BaseApiService {

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
}
