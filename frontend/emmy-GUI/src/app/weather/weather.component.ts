import { Component, OnInit } from '@angular/core';
import {LocationService} from "./shared/location.service";
import {Location} from "./shared/location.model";

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  title = "Weather"
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
