import { Component, OnInit } from '@angular/core';
import { Entry } from '../common/entry.model';

@Component({
  selector: 'app-blog-add',
  templateUrl: './blog-add.component.html',
  styleUrls: ['./blog-add.component.css']
})
export class BlogAddComponent implements OnInit {

  entry: Entry;

  constructor() { }

  ngOnInit() {
  }

}
