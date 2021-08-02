import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorDeleteFormComponent } from './author-delete-form.component';

describe('AuthorDeleteFormComponent', () => {
  let component: AuthorDeleteFormComponent;
  let fixture: ComponentFixture<AuthorDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthorDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
