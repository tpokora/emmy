import { HolidayService } from './common/holiday.service';
import { Component, OnInit } from '@angular/core';
import { Holiday } from './common/holiday.model';

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.css']
})
export class HolidaysComponent implements OnInit {

  holidays: Holiday[];

  constructor(
    private holidayService: HolidayService
  ) { }

  ngOnInit() {
    this.getHolidays();
  }

  getHolidays() {
    this.holidayService.getHolidays()
      .then(holidays => {
        this.holidays = holidays;
      });
  }

  isHolidaysEmpty(): boolean {
    return this.holidays == null || this.holidays.length === 0;
  }

}
