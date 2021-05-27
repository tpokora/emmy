import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private readonly GOGGLE_URL = 'http://google.pl';

  constructor() {}

  ngOnInit(): void {}

  redirectAction() {
    console.log("redirect")
    document.location.href = this.GOGGLE_URL;
  }

}
