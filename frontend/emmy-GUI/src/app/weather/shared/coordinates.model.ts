export class Coordinates {
  longitude: number | undefined;
  latitude: number | undefined;
  longitudeDM: number | undefined;
  latitudeDM: number | undefined;

  constructor(longitude?: number, latitude?: number, longitudeDM?: number, latitudeDM?: number) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.longitudeDM = longitudeDM;
    this.latitudeDM = latitudeDM;
  }
}
