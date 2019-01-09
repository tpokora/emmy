import { UserServiceStub, USERS } from './../../testing/user.stubs';
import { UserService } from './../common/user.service';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { UserListComponent } from './user-list.component';
import { MatListModule, MatCardModule } from '@angular/material';
import { By } from '@angular/platform-browser';

describe('UserListComponent', () => {
  const NO_USERS_STRING = 'No users!';
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

  function createComponent(withUsers: boolean) {
    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    component.users = null;
    if (withUsers) {
      component.users = USERS;
    }
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create', fakeAsync(() => {
    createComponent(true);
  }));

  it('should have users list elements', fakeAsync(() => {
    createComponent(true);
    expect(component.users.length > 0);
    const debugElement = getListElements();
    expect(debugElement.length).toEqual(component.users.length);
  }));

  it('should have user list element with text ' + NO_USERS_STRING, fakeAsync(() => {
    createComponent(false);
    component.users = null;
    expect(component.users == null);
    const debugElement = fixture.debugElement.queryAll(By.css('mat-card mat-card-content mat-list mat-list-item'));
    expect(debugElement[0].nativeElement.textContent).toEqual(NO_USERS_STRING);
  }));

  function getListElements() {
    return fixture.debugElement.queryAll(By.css('mat-card mat-card-content mat-list a'));
  }
});
