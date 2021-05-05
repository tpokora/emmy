import {Forecast} from "../../weather/shared/forecast.model";

export const FORECAST: Forecast = {
  location: 'testLocation',
  name: 'testName',
  description: 'testDescription',
  temp: 1.1,
  feelTemp: 1.2,
  minTemp: 1.0,
  maxTemp: 2.0,
  pressure: 1000,
  humidity: 70,
  wind: 3.2,
  rain1h: 1,
  rain3h: 3,
  longitude: 11.11,
  latitude: 22.22,
  timestamp: new Date(2021, 9, 11, 22, 11, 32)
}
