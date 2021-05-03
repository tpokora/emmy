import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs/operators";
import {Location} from "./location.model";
import {Coordinates} from "./coordinates.model";
import {Observable} from "rxjs";
import {LocationAdapter} from "./location.adapter";

@Injectable()
export class LocationService {
  constructor(private http: HttpClient, private adapter: LocationAdapter) {
  }

  get(name: string) : Observable<Location>{
    return this.http.get("http://localhost:8080/api/location?name=" + name)
      .pipe(
          map((data: any) => this.adapter.adapt(data))
      );
  }
}
