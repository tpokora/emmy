export class Location {
  name: string | undefined;
  longitude: number | undefined;
  latitude: number | undefined;

  constructor(name?: string, longitude?: number, latitude?: number) {
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
