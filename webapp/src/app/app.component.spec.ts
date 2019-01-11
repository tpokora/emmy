import { MaterialModule } from './material-module';
import { UserListComponent } from './users/user-list/user-list.component';
import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';
import { RouterModule, Routes, Router } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RouterStub } from './testing/routing.stubs';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user-list', component: UserListComponent },
];

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        NavBarComponent,
        DashboardComponent,
        UserListComponent
      ],
      imports: [
        RouterModule.forRoot(routes),
        MaterialModule
      ],
      providers: [
        { provide: Router, useClass: RouterStub },
      ]
    }).compileComponents();
  }));

  // it('should create the app', async(() => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   const app = fixture.debugElement.componentInstance;
  //   expect(app).toBeTruthy();
  // }));
});
