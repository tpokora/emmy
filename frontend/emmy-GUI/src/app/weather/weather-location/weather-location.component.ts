import { Component, OnInit } from '@angular/core';
import {Location} from "../shared/location.model";
import {LocationService} from "../shared/location.service";

@Component({
  selector: 'app-weather-location',
  templateUrl: './weather-location.component.html',
  styleUrls: ['./weather-location.component.css']
})
export class WeatherLocationComponent implements OnInit {

  formColor = '#b6b6b6';
  location= new Location();

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
  }

  getLocation() {
    let locationName = (<HTMLInputElement>document.getElementById("locationFormInput")).value;
    console.log("locationName: " + locationName)
    this.locationService.get(locationName)
      .subscribe((data: any) => this.location = {
        name: data.name,
        coordinates: data.coordinates
      });
  }

  validLocation() {
    return this.location.name != undefined;
  }
}
