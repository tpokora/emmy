import { LoginServiceStub } from './../testing/login.stubs';
import { LoginService } from './../login/common/login.service';
import { MatCardModule } from '@angular/material';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';
import { USERS } from '../testing/user.stubs';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  const user = USERS[0];

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
      imports: [
        MatCardModule
      ],
      providers: [
        { provide: LoginService, useClass: LoginServiceStub }
      ]
    })
    .compileComponents();
  }));

  function createComponent(withUser: boolean) {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
    if (withUser) {
      component.user = user;
    }
    expect(component).toBeTruthy();
  }

  it('should create without user', fakeAsync(() => {
    createComponent(false);
    expect(component.user.username).toBeUndefined();
    expect(component.getMessage()).toEqual('Welcome!');
  }));

  it('should create with user', fakeAsync(() => {
    createComponent(true);
    expect(component.user.username).toEqual(user.username);
    expect(component.getMessage()).toEqual('Welcome ' + user.username + '!');
  }));
});
