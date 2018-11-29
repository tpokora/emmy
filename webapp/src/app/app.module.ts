import { AppRoutingModule } from './app-routing.module';
import { MatCardModule, MatListModule, MatToolbarModule, MatIconModule, MatButtonModule } from '@angular/material';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserService } from './users/common/user.service';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list/user-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserDetailsComponent } from './users/user-details/user-details.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    NavBarComponent,
    DashboardComponent,
    UserDetailsComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatListModule
  ],
  exports: [ RouterModule ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
