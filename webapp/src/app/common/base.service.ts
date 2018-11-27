import { environment } from './../../environments/environment';
import {Headers} from '@angular/http';

export abstract class BaseApiService {

  protected url = environment.api_url;
  protected headers = new Headers({'Content-Type': 'application/json'});

  constructor() { }

  protected generateHeaders(): Headers {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  protected handleError(error: any): Promise<any> {
    return Promise.resolve(null);
  }
}