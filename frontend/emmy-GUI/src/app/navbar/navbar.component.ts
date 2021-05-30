import {Component, OnInit} from '@angular/core';
import {RestService} from "../core/rest.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private redirectResponse: string;

  constructor(private restService: RestService<String>) {
  }

  ngOnInit(): void {
  }

  redirectAction() {
    console.log("redirect")
    this.restService.get('misc/redirect')
      .subscribe((data: string) => {
        this.redirectResponse = data;
      })
  }

}
