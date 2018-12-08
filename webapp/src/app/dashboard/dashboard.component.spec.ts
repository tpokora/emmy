import { LoginServiceStub } from './../testing/login.stubs';
import { LoginService } from './../login/common/login.service';
import { MatCardModule } from '@angular/material';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

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

  function createComponent() {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create', fakeAsync(() => {
    createComponent();
  }));
});
