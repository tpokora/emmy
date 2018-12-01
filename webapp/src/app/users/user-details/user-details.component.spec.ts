import { ActivatedRouteStub } from './../../testing/routing.stubs';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { UserDetailsComponent } from './user-details.component';
import { UserService } from '../common/user.service';
import { UserServiceStub, USERS } from 'src/app/testing/user.stubs';

fdescribe('UserDetailsComponent', () => {
  const TEST_USER_USERNAME = 'testUser';
  const TEST_USER = USERS.filter(user => user.username === TEST_USER_USERNAME)[0];
  let component: UserDetailsComponent;
  let fixture: ComponentFixture<UserDetailsComponent>;
  let activatedRoute: ActivatedRouteStub;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetailsComponent ],
      imports: [
        MatCardModule
      ],
      providers: [
        { provide: UserService, useClass: UserServiceStub },
        { provide: ActivatedRoute, useClass: ActivatedRouteStub },
        { provide: Location }
      ]
    })
    .compileComponents();
  }));

  function createComponent() {
    fixture = TestBed.createComponent(UserDetailsComponent);
    component = fixture.componentInstance;
    activatedRoute = fixture.debugElement.injector.get(ActivatedRoute) as any;
    activatedRoute.testParamMap = {username: TEST_USER_USERNAME};

    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create', fakeAsync(() => {
    createComponent();
  }));

  it('should have testUser', fakeAsync(() => {
    createComponent();
    expect(component.user).toBeTruthy();
    expect(component.user.username).toEqual(TEST_USER.username);
  }));
});
