import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {ForecastAdapter} from "./forecast.adapter";
import {Forecast} from "./forecast.model";
import {RestService} from "../../core/rest.service";

@Injectable()
export class ForecastService {
  constructor(private restService: RestService<Forecast>, private adapter: ForecastAdapter) {
  }

  get(location: string): Observable<Forecast> {
    return this.restService.get("weather/forecast?location=" + location, this.adapter)
  }
}
