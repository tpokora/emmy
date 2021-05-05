import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {Location} from "../shared/location.model";
import {LocationService} from "../shared/location.service";
import {Coordinates} from "../shared/coordinates.model";

@Component({
  selector: 'app-weather-location',
  templateUrl: './weather-location.component.html',
  styleUrls: ['./weather-location.component.css']
})
export class WeatherLocationComponent implements OnInit {

  formColor = '#b6b6b6';
  location: Location;
  @Output()
  locationEmitter = new EventEmitter<Location>();

  constructor(private locationService: LocationService) {
    this.location = new Location("", new Coordinates(0, 0, 0, 0))
  }

  ngOnInit(): void {
  }

  getLocation() {
    let locationName = (<HTMLInputElement>document.getElementById("locationFormInput")).value;
    this.locationService.get(locationName)
      .subscribe((data: Location) => {
        this.location = data;
        this.locationEmitter.emit(this.location)
      });
  }

  validLocation() {
    return this.location.name != '';
  }
}
