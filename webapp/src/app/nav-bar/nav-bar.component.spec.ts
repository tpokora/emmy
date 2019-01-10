import { USERS } from 'src/app/testing/user.stubs';
import { MatToolbarModule, MatIconModule, MatButtonModule } from '@angular/material';
import { async, ComponentFixture, TestBed, tick, fakeAsync, inject } from '@angular/core/testing';
import { NavBarComponent } from './nav-bar.component';
import { RouterModule, Routes } from '@angular/router';
import { LoginService } from '../login/common/login.service';
import { LoginServiceStub } from '../testing/login.stubs';
import { By } from '@angular/platform-browser';
import { when } from 'q';
import { Observable, of } from 'rxjs';
import { User } from '../users/common/user.model';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];

fdescribe('NavBarComponent', () => {
  let component: NavBarComponent;
  let fixture: ComponentFixture<NavBarComponent>;
  let loginService;

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
        { provide: LoginService, useClass: LoginServiceStub }
      ]
    })
    .compileComponents();
  }));

  beforeEach(inject([LoginService], service => {
    loginService = service;
    fixture = TestBed.createComponent(NavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create with logged out user', fakeAsync(() => {
    createComponent(false);
    const debugElements = getListElements();
    const loginBtn = debugElements[debugElements.length - 1];
    expect(loginBtn.nativeElement.textContent).toEqual('Login');
  }));

  it('should create with logged user', fakeAsync(() => {
    createComponent(true);
    const debugElement = getListElements();
    const logoutBtn = debugElement[debugElement.length - 1];
    expect(logoutBtn.nativeElement.textContent).toEqual('Logout');
    const userBtn = debugElement[debugElement.length - 2];
    expect(userBtn.nativeElement.textContent).toEqual(USERS[0].username);
  }));

  function createComponent(loggedUser: boolean) {
    fixture = TestBed.createComponent(NavBarComponent);
    component = fixture.componentInstance;
    if (loggedUser) {
      spyOn(loginService, 'getUser').and.returnValue(of(USERS[0]));
    }
    component.ngOnInit();
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  function getListElements() {
    return fixture.debugElement.queryAll(By.css('mat-toolbar mat-toolbar-row button'));
  }

});
