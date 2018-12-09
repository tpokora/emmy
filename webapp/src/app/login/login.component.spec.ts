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
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
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
  }

  it('should create', fakeAsync(() => {
    createComponent();
  }));

  it('clicked button should log in user and get auth tokens', fakeAsync(() => {
    createComponent();
    component.login = LOGIN;
    component.loginAction();
    tick();
    expect(component.user.username).toEqual(USERS[0].username);
    expect(component.auth.refresh_token).toEqual(AUTH.refresh_token);
    expect(component.auth.access_token).toEqual(AUTH.access_token);
  }));
});
