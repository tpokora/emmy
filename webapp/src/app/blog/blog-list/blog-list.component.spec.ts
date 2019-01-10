import { BlogServiceStub, ENTRIES } from './../../testing/blog.stubs';
import { BlogService } from './../common/blog.service';
import { BlogAddComponent } from './../blog-add/blog-add.component';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { BlogListComponent } from './blog-list.component';
import { MatCardModule, MatListModule, MatExpansionModule, MatInputModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';

describe('BlogListComponent', () => {
  let component: BlogListComponent;
  let fixture: ComponentFixture<BlogListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        BlogListComponent,
        BlogAddComponent
       ],
      imports: [
        BrowserAnimationsModule,
        MatCardModule,
        MatListModule,
        MatInputModule,
        MatExpansionModule,
        ReactiveFormsModule,
      ],
      providers: [
        { provide: BlogService, useClass: BlogServiceStub }
      ]
    })
    .compileComponents();
  }));

  function createComponent(withEntries: boolean) {
    fixture = TestBed.createComponent(BlogListComponent);
    component = fixture.componentInstance;
    component.entries = null;
    if (withEntries) {
      component.entries = ENTRIES;
    }
    fixture.detectChanges();
    tick();
    expect(component).toBeTruthy();
  }

  it('should create with entries', fakeAsync(() => {
    createComponent(true);
    expect(component.entries.length > 0);
    const debugElement = getListElements();
    expect(debugElement.length).toEqual(component.entries.length + 1);
  }));

  it('should create without entries', fakeAsync(() => {
    createComponent(false);
    expect(component.entries === null);
    const debugElement = getListElements();
    expect(debugElement.length).toEqual(1);
  }));

  function getListElements() {
    return fixture.debugElement.queryAll(By.css('mat-card mat-card-content mat-accordion mat-expansion-panel'));
  }
});
