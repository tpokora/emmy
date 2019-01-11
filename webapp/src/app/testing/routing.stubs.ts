import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { convertToParamMap, ParamMap, NavigationEnd } from '@angular/router';

@Injectable()
export class ActivatedRouteStub {

    // Observable that contains a map of the parameters
    private subjectParamMap = new BehaviorSubject(convertToParamMap(this.testParamMap));
    paramMap = this.subjectParamMap.asObservable();

    private _testParamMap: ParamMap;
    get testParamMap() {
        return this._testParamMap;
    }
    set testParamMap(params: {}) {
        this._testParamMap = convertToParamMap(params);
        this.subjectParamMap.next(this._testParamMap);
    }

    // Observable that contains a map of the query parameters
    private subjectQueryParamMap = new BehaviorSubject(convertToParamMap(this.testParamMap));
    queryParamMap = this.subjectQueryParamMap.asObservable();

    private _testQueryParamMap: ParamMap;
    get testQueryParamMap() {
        return this._testQueryParamMap;
    }
    set testQueryParamMap(params: {}) {
        this._testQueryParamMap = convertToParamMap(params);
        this.subjectQueryParamMap.next(this._testQueryParamMap);
    }

    get snapshot() {
        return {
            paramMap: this.testParamMap,
            queryParamMap: this.testQueryParamMap
        };
    }
}

@Injectable()
export class RouterStub {
  public ne = new NavigationEnd(0, '/', '/');
  public events = new Observable(observer => {
    observer.next(this.ne);
    observer.complete();
  });

  navigate(commands: any[]) {
    return commands;
  }
}
