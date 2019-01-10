import { BaseApiService } from 'src/app/common/base.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Entry } from './entry.model';

@Injectable()
export class BlogService extends BaseApiService {

  constructor(private http: Http) {
    super();
  }

  getBlogEntries(): Promise<Entry[]> {
    const url = `${this.url}/blog/entries`;
    return this.http.get(url, { headers: this.generateHeadersWithToken(true) })
      .toPromise()
      .then(response => response.json().results as Entry[])
      .catch(this.handleError);
  }
}
