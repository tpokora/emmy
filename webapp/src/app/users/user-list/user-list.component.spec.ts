import { UserServiceStub } from './../../testing/user.stubs';
import { UserService } from './../common/user.service';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { UserListComponent } from './user-list.component';
import { MatListModule, MatCardModule } from '@angular/material';
import { By } from '@angular/platform-browser';

describe('UserListComponent', () => {
  let NO_USERS_STRING = "No users!";
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserListComponent ],
      imports: [
        MatCardModule,
        MatListModule
      ],
      providers: [
        { provide: UserService, useClass: UserServiceStub }
      ]
    })
    .compileComponents();
  }));

  function createComponent() {
    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create', fakeAsync(() => {
    createComponent();
  }));

  it('should have users list elements', fakeAsync(() => {
    createComponent();
    expect(component.users.length > 0);
    let debugElement = getListElements();
    expect(debugElement.length).toEqual(component.users.length);
  }));

  it('should have user list element with text ' + NO_USERS_STRING, fakeAsync(() => {
    createComponent();
    component.users = null;
    expect(component.users == null);
    let debugElement = getListElements();
    expect(debugElement[0].nativeElement.textContent).toEqual(NO_USERS_STRING);
  }));

  function getListElements() {
    return fixture.debugElement.queryAll(By.css('mat-card mat-card-content mat-list mat-list-item'));
  }
});
