import { Component, OnInit } from '@angular/core';
import { ENTRIES } from 'src/app/testing/entry.stubs';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  panelOpenState = false;
  entries = ENTRIES;

  constructor() { }

  ngOnInit() {
  }

}
