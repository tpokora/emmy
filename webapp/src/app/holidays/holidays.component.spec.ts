import { HolidayServiceStub } from './../testing/holiday.stubs';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidaysComponent } from './holidays.component';
import { MatCardModule, MatListModule } from '@angular/material';
import { HolidayService } from './common/holiday.service';

describe('HolidaysComponent', () => {
  let component: HolidaysComponent;
  let fixture: ComponentFixture<HolidaysComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HolidaysComponent ],
      imports: [
        MatCardModule,
        MatListModule
      ],
      providers: [
        { provide: HolidayService, useClass: HolidayServiceStub }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HolidaysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
