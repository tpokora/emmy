import {Coordinates} from "./coordinates.model";

export class Location {
  name: string | undefined;
  coordinates: Coordinates | undefined

  constructor(name?: string, coordinates?: Coordinates) {
    this.name = name;
    this.coordinates = coordinates;
  }
}
