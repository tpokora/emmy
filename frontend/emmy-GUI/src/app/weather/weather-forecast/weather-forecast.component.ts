import {Component, Input, OnInit} from '@angular/core';
import {Location} from "../shared/location.model";
import {Coordinates} from "../shared/coordinates.model";
import {ForecastService} from "../shared/forecast.service";
import {Forecast} from "../shared/forecast.model";

@Component({
  selector: 'app-weather-forecast',
  templateUrl: './weather-forecast.component.html',
  styleUrls: ['./weather-forecast.component.css']
})
export class WeatherForecastComponent implements OnInit {

  @Input()
  location: Location
  forecast: Forecast

  constructor(private forecastService: ForecastService) {
    this.location = new Location("", new Coordinates(0, 0, 0, 0))
    this.forecast = Forecast.createEmptyForecast()
  }

  ngOnInit(): void {
  }

  getForecast() {
    this.forecastService.get(this.location.name)
      .subscribe((data: Forecast) => {
        this.forecast = data;
        console.log(this.forecast)
      })
  }

  validForecast() {
    return this.forecast.name != '';
  }

}
