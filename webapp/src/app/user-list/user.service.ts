import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { User } from './user.model';
import { BaseApiService } from '../common/base.service';

@Injectable()
export class UserService extends BaseApiService {

  constructor(private http: Http) {
    super();
  }

  getUsers(): Promise<User[]> {
    let url = `${this.url}/user`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as User[])
      .catch(this.handleError);
  }
}