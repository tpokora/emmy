import { UserServiceStub } from './../../testing/user.stubs';
import { UserService } from './../common/user.service';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { UserListComponent } from './user-list.component';
import { MatListModule, MatCardModule } from '@angular/material';

describe('UserListComponent', () => {
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
  }

  it('should create', fakeAsync(() => {
    createComponent();
    expect(component).toBeTruthy();
  }));
});
