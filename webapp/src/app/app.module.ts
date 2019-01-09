import { HolidayService } from './holidays/common/holiday.service';
import { LoginService } from './login/common/login.service';
import { MaterialModule } from './material-module';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserService } from './users/common/user.service';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list/user-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserDetailsComponent } from './users/user-details/user-details.component';
import { LoginComponent } from './login/login.component';
import { HolidaysComponent } from './holidays/holidays.component';
import { BlogListComponent } from './blog/blog-list/blog-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    NavBarComponent,
    DashboardComponent,
    UserDetailsComponent,
    LoginComponent,
    HolidaysComponent,
    BlogListComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    MaterialModule,
  ],
  exports: [ RouterModule ],
  providers: [
    UserService,
    LoginService,
    HolidayService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
