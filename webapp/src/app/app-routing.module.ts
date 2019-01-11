import { HolidaysComponent } from './holidays/holidays.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserListComponent } from './users/user-list/user-list.component';
import { UserDetailsComponent } from './users/user-details/user-details.component';
import { LoginComponent } from './login/login.component';
import { BlogListComponent } from './blog/blog-list/blog-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user-list', component: UserListComponent },
  { path: 'user-details/:username', component: UserDetailsComponent },
  { path: 'holidays', component: HolidaysComponent },
  { path: 'blog', component: BlogListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
