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
    this.locationService.get("Skawina")
      .subscribe((data: any) => {
        console.log(data)
        this.location = data
    });
  }

}
