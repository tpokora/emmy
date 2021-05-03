import {Component, Input, OnInit} from '@angular/core';
import {Location} from "./shared/location.model";
import {Coordinates} from "./shared/coordinates.model";

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  title = "Weather"
  location: Location

  constructor() {
    this.location = new Location("", new Coordinates(0, 0, 0, 0))
  }

  ngOnInit(): void {
  }

  onLocationSearch(location: Location) {
    console.log("location from child: " + location.name)
    this.location = location;
  }
}
