import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDeleteFormComponent } from './book-delete-form.component';

describe('BookDeleteFormComponent', () => {
  let component: BookDeleteFormComponent;
  let fixture: ComponentFixture<BookDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
