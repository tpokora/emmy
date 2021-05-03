import {Injectable} from "@angular/core";
import {Location} from "./location.model";
import {Coordinates} from "./coordinates.model";
import {Adapter} from "../../core/adapter";

@Injectable({
  providedIn: 'root'
})
export class LocationAdapter implements Adapter<Location> {

  adapt(item: any): Location {
    return new Location(item.name,
        new Coordinates(item.coordinates.longitude, item.coordinates.latitude, 0, 0)
    );
  }
}
