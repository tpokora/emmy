import { ActivatedRouteStub } from './../../testing/routing.stubs';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material';
import { async, ComponentFixture, TestBed, fakeAsync } from '@angular/core/testing';

import { UserDetailsComponent } from './user-details.component';
import { UserService } from '../common/user.service';
import { UserServiceStub } from 'src/app/testing/user.stubs';

describe('UserDetailsComponent', () => {
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

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailsComponent);
    component = fixture.componentInstance;

    activatedRoute = fixture.debugElement.injector.get(ActivatedRoute) as any;
    activatedRoute.testParamMap = {username: 'testUser'};

    fixture.detectChanges();
  });

  it('should create', fakeAsync(() => {
    expect(component).toBeTruthy();
  }));
});
