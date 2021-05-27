import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";

export class HttpErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // intercept and modify request
    console.log('request: ' + this.getStringValue(req));
    return next.handle(req).pipe(
      map(response => {
        // intercept response
        console.log('response: ' + this.getStringValue(response));
        return response;
      }),
      catchError(error => {
          // intercept error
          console.log('error: ' + this.getStringValue(error));
          return throwError(error);
        }
      )
    );
  }

  private getStringValue(object: Object): string {
    return JSON.stringify(object);
  }


}
