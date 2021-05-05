import {Injectable} from "@angular/core";
import {FORECAST} from "./data/forecast";
import {of} from "rxjs";

@Injectable()
export class ForecastServiceStubs {

  get(location: string) {
    return of(FORECAST)
  }
}
