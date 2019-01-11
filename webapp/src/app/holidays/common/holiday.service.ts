import { Holiday } from './holiday.model';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BaseApiService } from '../../common/base.service';

@Injectable()
export class HolidayService extends BaseApiService {

  constructor(private http: Http) {
    super();
  }

  getHolidays(): Promise<Holiday[]> {
    const url = `${this.url}/holidays`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().results as Holiday[])
      .catch(this.handleError);
  }

  getHolidayById(id: number): Promise<Holiday> {
    const url = `${this.url}/holidays/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Holiday)
      .catch(this.handleError);
  }

}
