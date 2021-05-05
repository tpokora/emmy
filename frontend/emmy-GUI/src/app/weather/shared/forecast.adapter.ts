import {Injectable} from "@angular/core";
import {Adapter} from "../../core/adapter";
import {Forecast} from "./forecast.model";

@Injectable({
  providedIn: 'root'
})
export class ForecastAdapter implements Adapter<Forecast> {

  adapt(item: any): Forecast {
    return new Forecast(item.location, item.name, item.description, item.temp,
      item.feelTemp, item.minTemp, item.maxTemp, item.pressure, item.humidity,
      item.wind, item.rain1h, item.rain3h, item.longitude, item.latitude,
      item.timestamp
    );
  }
}
