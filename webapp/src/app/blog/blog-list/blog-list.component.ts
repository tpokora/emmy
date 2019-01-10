import { BlogService } from './../common/blog.service';
import { Component, OnInit } from '@angular/core';
import { ENTRIES } from 'src/app/testing/blog.stubs';
import { Entry } from '../common/entry.model';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit {

  panelOpenState = false;
  entries: Entry[];

  constructor(private blogService: BlogService) { }

  ngOnInit() {
    this.blogService.getBlogEntries()
      .then(entries => this.entries = entries);
  }

}
