import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailsComponent } from './user-details.component';
import { UserService } from '../common/user.service';
import { UserServiceStub } from 'src/app/testing/user.stubs';

describe('UserDetailsComponent', () => {
  let component: UserDetailsComponent;
  let fixture: ComponentFixture<UserDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetailsComponent ],
      imports: [
        MatCardModule
      ],
      providers: [
        { provide: UserService, useClass: UserServiceStub },
        ActivatedRoute
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
