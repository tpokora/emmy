import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';

import {WeatherComponent} from './weather.component';
import {LocationServiceStubs} from "../testing/location-stubs";
import {LocationService} from "./shared/location.service";

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

});
