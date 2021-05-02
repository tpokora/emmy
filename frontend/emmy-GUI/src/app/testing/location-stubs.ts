import {Injectable} from "@angular/core";
import {LOCATION} from "./data/location";
import {of} from "rxjs";

@Injectable()
export class LocationServiceStubs {

  get(name: string) {
    return of(LOCATION)
  }
}
