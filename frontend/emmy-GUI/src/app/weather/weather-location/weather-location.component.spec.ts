import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';

import { WeatherLocationComponent } from './weather-location.component';
import {WeatherComponent} from "../weather.component";
import {By} from "@angular/platform-browser";
import {LocationService} from "../shared/location.service";
import {LocationServiceStubs} from "../../testing/location-stubs";

describe('WeatherLocationComponent', () => {
  let component: WeatherLocationComponent;
  let fixture: ComponentFixture<WeatherLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeatherLocationComponent ],
      providers: [
        {provide: LocationService, useClass: LocationServiceStubs}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show location coordinates when clicked Find button', fakeAsync(() => {
    const fixture = TestBed.createComponent(WeatherLocationComponent);
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css("div#locationResult"))).toBeFalsy();
    let locationFormBtn = fixture.debugElement.query(By.css("button#locationFormBtn"))
    expect(locationFormBtn).toBeTruthy()
    locationFormBtn.triggerEventHandler('click', null);
    tick();
    fixture.detectChanges();
    let locationResult = fixture.debugElement.query(By.css("div#locationResult"))
    expect(locationResult).toBeTruthy()
    expect(locationResult).withContext("Longitude: 11.11")
    expect(locationResult).withContext("Latitude: 11.11")
  }));
});
