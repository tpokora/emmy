import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogAddComponent } from './blog-add.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatCardModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';

describe('BlogAddComponent', () => {
  let component: BlogAddComponent;
  let fixture: ComponentFixture<BlogAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlogAddComponent ],
      imports: [
        BrowserAnimationsModule,
        MatInputModule,
        ReactiveFormsModule,
        MatCardModule
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlogAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
