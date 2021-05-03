import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';

import {WeatherComponent} from './weather.component';
import {LocationServiceStubs} from "../testing/location-stubs";
import {LocationService} from "./shared/location.service";
import {By} from "@angular/platform-browser";

describe('WeatherComponent', () => {
  let component: WeatherComponent;
  let fixture: ComponentFixture<WeatherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WeatherComponent],
      imports: [],
      providers: [
        {provide: LocationService, useClass: LocationServiceStubs}
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeatherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have title', () => {
    const fixture = TestBed.createComponent(WeatherComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain(compiled.title);
  });

  it('should show location coordinates when clicked Find button', fakeAsync(() => {
    const fixture = TestBed.createComponent(WeatherComponent);
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
