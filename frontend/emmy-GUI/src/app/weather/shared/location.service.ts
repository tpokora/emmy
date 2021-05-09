import {Injectable} from "@angular/core";
import {Location} from "./location.model";
import {Observable} from "rxjs";
import {LocationAdapter} from "./location.adapter";
import {RestService} from "../../core/rest.service";

@Injectable()
export class LocationService {
  constructor(private restService: RestService<Location>, private adapter: LocationAdapter) {
  }

  get(name: string): Observable<Location> {
    return this.restService.get("location?name=" + name, this.adapter);
  }
}
