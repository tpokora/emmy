import {Component, Input, OnInit} from '@angular/core';
import {Location} from "../shared/location.model";
import {Coordinates} from "../shared/coordinates.model";

@Component({
  selector: 'app-weather-forecast',
  templateUrl: './weather-forecast.component.html',
  styleUrls: ['./weather-forecast.component.css']
})
export class WeatherForecastComponent implements OnInit {

  @Input()
  location: Location

  constructor() {
    this.location = new Location("", new Coordinates(0, 0, 0, 0))
  }

  ngOnInit(): void {
  }

}
