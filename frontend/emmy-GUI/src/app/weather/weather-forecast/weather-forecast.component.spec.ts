import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';

import { WeatherForecastComponent } from './weather-forecast.component';
import {ForecastService} from "../shared/forecast.service";
import {ForecastServiceStubs} from "../../testing/weather-stubs";
import {WeatherLocationComponent} from "../weather-location/weather-location.component";
import {By} from "@angular/platform-browser";
import {LocationService} from "../shared/location.service";
import {LocationServiceStubs} from "../../testing/location-stubs";
import {LOCATION} from "../../testing/data/location";

describe('WeatherForecastComponent', () => {
  let component: WeatherForecastComponent;
  let fixture: ComponentFixture<WeatherForecastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeatherForecastComponent ],
      providers: [
        {provide: LocationService, useClass: LocationServiceStubs},
        {provide: ForecastService, useClass: ForecastServiceStubs}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherForecastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show forecast when clicked Find Forecast button', fakeAsync(() => {
    const fixture = TestBed.createComponent(WeatherForecastComponent);
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css("div#forecastResults"))).toBeFalsy();
    let findForecastBtn = fixture.debugElement.query(By.css("button#forecastFormBtn"))
    expect(findForecastBtn).toBeTruthy()
    component.location = LOCATION
    findForecastBtn.triggerEventHandler('click', null);
    tick();
    fixture.detectChanges()
    let forecastResult = fixture.debugElement.query(By.css("div#forecastResults"))
    expect(forecastResult).toBeTruthy();
    expect(forecastResult).withContext(`>> Name: ${component.forecast.name}, Location: ${component.forecast.location}`)
    expect(forecastResult).withContext(`>> Description: ${component.forecast.description}`)
    expect(forecastResult).withContext(`>> Temp: ${component.forecast.temp}, FeelTemp: ${component.forecast.feelTemp}`)
    expect(forecastResult).withContext(`>> MinTemp: ${component.forecast.minTemp}, MaxTemp: ${component.forecast.maxTemp}`)
    expect(forecastResult).withContext(`>> Pressure: ${component.forecast.pressure}, Humidity: ${component.forecast.humidity}`)
    expect(forecastResult).withContext(`>> Wind: ${component.forecast.wind}`)
    expect(forecastResult).withContext(`>> Rain1h: ${component.forecast.rain1h}, Rain3h: ${component.forecast.rain3h}`)
    expect(forecastResult).withContext(`>> Longitude: ${component.forecast.longitude}, Latitude: ${component.forecast.latitude}`)
    expect(forecastResult).withContext(`>> Timestamp: ${component.forecast.timestamp}`)
  }));
});
