import { Component, OnInit } from '@angular/core';
import { UserService } from '../common/user.service';
import { User } from '../common/user.model';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  user: User;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location
  ) { }

  ngOnInit() {
    this.user = new User();
    this.getUser();
  }

  getUser(): void {
    const username = this.route.snapshot.paramMap.get('username');
    this.userService.getUser(username)
      .then(user =>
        this.user = user);
  }

  goBack(): void {
    this.location.back();
  }

}
