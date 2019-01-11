import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';
import { Holiday } from '../holidays/common/holiday.model';

export const HOLIDAYS: Holiday[] = [
  {
    id: 1,
    title: 'holiday',
    deadline: new Date()
  },
];

@Injectable()
export class HolidayServiceStub {

  getHolidays(): Promise<Holiday[]> {
    return Promise.resolve(HOLIDAYS);
  }

  getHolidayById(id: number): Promise<Holiday> {
    const holiday = HOLIDAYS.filter(eachHoliday => eachHoliday.id === id)[0];
    return Promise.resolve(holiday);
  }
}
