import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ForecastAdapter} from "./forecast.adapter";
import {Forecast} from "./forecast.model";

@Injectable()
export class ForecastService {
  constructor(private http: HttpClient, private adapter: ForecastAdapter) {
  }

  get(location: string) : Observable<Forecast>{
    return this.http.get("http://localhost:8080/api/weather/forecast?location=" + location)
      .pipe(
        map((data: any) => this.adapter.adapt(data))
      );
  }
}
