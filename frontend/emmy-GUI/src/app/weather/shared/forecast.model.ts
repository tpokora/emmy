export class Forecast {
  constructor(public location: string, public name: string, public description: string,
              public temp: number, public feelTemp: number, public minTemp: number,
              public maxTemp: number, public pressure: number, public humidity: number,
              public wind: number, public rain1h: number, public rain3h: number,
              public longitude: number, public latitude: number, public timestamp: Date) {
  }

  static createEmptyForecast() {
    return new Forecast('', '', '', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new Date())
  }
}
