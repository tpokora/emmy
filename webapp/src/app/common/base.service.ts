import { environment } from './../../environments/environment';
import {Headers} from '@angular/http';

export abstract class BaseApiService {

  protected url = environment.api_url;

  protected readonly ACCESS_TOKEN = 'access_token';
  protected readonly REFRESH_TOKEN = 'refresh_token';
  protected readonly TOKEN = 'token';

  constructor() { }

  protected generateHeaders(): Headers {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  protected generateHeadersWithToken(accessToken: boolean) {
    const headers = this.generateHeaders();
    let token = '';
    if (accessToken) {
      token += this.getAccessToken();
    } else {
      token += this.getRefreshToken();
    }
    if (token === '') {
      token = this.getToken();
    }
    headers.append('Authorization', 'Bearer ' + token);
    return headers;
  }

  private getRefreshToken() {
    return sessionStorage.getItem(this.REFRESH_TOKEN);
  }

  private getAccessToken() {
    return sessionStorage.getItem(this.ACCESS_TOKEN);
  }

  private getToken() {
    return sessionStorage.getItem(this.TOKEN);
  }

  protected handleError(error: any): Promise<any> {
    return Promise.resolve(null);
  }
}
