import { MatToolbarModule, MatIconModule, MatButtonModule } from '@angular/material';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { NavBarComponent } from './nav-bar.component';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from '../login/common/login.service';
import { LoginServiceStub } from '../testing/login.stubs';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];

describe('NavBarComponent', () => {
  let component: NavBarComponent;
  let fixture: ComponentFixture<NavBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavBarComponent ],
      imports: [
        MatToolbarModule,
        MatButtonModule,
        MatIconModule,
        RouterModule.forRoot(routes),
      ],
      providers: [
        { provide: LoginService, useClass: LoginServiceStub },
      ]
    })
    .compileComponents();
  }));

  function createComponent() {
    fixture = TestBed.createComponent(NavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create', fakeAsync(() => {
    createComponent();
  }));
});
