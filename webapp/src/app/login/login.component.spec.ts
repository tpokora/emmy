import { AUTH, LOGIN } from './../testing/login.stubs';
import { LoginService } from './common/login.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { MatCardModule, MatInputModule } from '@angular/material';
import { LoginServiceStub } from '../testing/login.stubs';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterStub } from '../testing/routing.stubs';
import { UserService } from '../users/common/user.service';
import { UserServiceStub, USERS } from '../testing/user.stubs';

describe('LoginComponent', () => {
  const ACCESS_TOKEN = 'access_token';
  const REFRESH_TOKEN = 'refresh_token';
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  const sessionStorageMock: Map<string, any> = new Map<string, any>();

  beforeEach(async(() => {
    sessionStorageMock.clear();
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [
        MatCardModule,
        MatInputModule,
        BrowserAnimationsModule,
        FormsModule
      ],
      providers: [
        { provide: LoginService, useClass: LoginServiceStub },
        { provide: UserService, useClass: UserServiceStub},
        { provide: Router, useClass: RouterStub }
      ]
    })
    .compileComponents();

    spyOn(sessionStorage, 'getItem').and.callFake((key) => {
      return sessionStorageMock.get(key);
    });

    spyOn(sessionStorage, 'setItem').and.callFake((key, value) => {
      sessionStorageMock.set(key, value);
    });

    spyOn(sessionStorage, 'removeItem').and.callFake((key) => {
      sessionStorageMock.delete(key);
    });
  }));

  it('should create', fakeAsync(() => {
    createComponent();
  }));

  it('should login user and store auth tokens in session', fakeAsync(() => {
    createComponent();
    login();
  }));

  it('should logout user and remove auth tokens from session', fakeAsync(() => {
    createComponent();
    login();
    logout();
  }));

  function createComponent() {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
    expect(component.auth).toBeUndefined();
    expect(component.user).toBeUndefined();
    expect(component.login.username).toBeUndefined();
    expect(component.login.password).toBeUndefined();
    expect(sessionStorageMock.get(ACCESS_TOKEN)).toBeUndefined();
    expect(sessionStorageMock.get(REFRESH_TOKEN)).toBeUndefined();
  }

  function login() {
    component.login = LOGIN;
    component.loginAction();
    tick();
    expect(component.user.username).toEqual(USERS[0].username);
    expect(component.auth.refresh_token).toEqual(AUTH.refresh_token);
    expect(component.auth.access_token).toEqual(AUTH.access_token);
    expect(sessionStorageMock.get(ACCESS_TOKEN)).toEqual(AUTH.access_token);
    expect(sessionStorageMock.get(REFRESH_TOKEN)).toEqual(AUTH.refresh_token);
  }

  function logout() {
    component.logoutAction();
    tick();
    expect(component.auth).toBeUndefined();
    expect(component.user).toBeUndefined();
    expect(component.login.username).toBeUndefined();
    expect(component.login.password).toBeUndefined();
    expect(sessionStorageMock.get(ACCESS_TOKEN)).toBeUndefined();
    expect(sessionStorageMock.get(REFRESH_TOKEN)).toBeUndefined();
  }
});
