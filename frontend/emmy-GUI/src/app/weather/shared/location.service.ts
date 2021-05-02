import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class LocationService {
  constructor(private http: HttpClient) {
  }

  get(name: string) {
    return this.http.get("http://localhost:8080/api/location?name=" + name)
  }
}
