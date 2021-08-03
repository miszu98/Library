import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDisplayFormComponent } from './user-display-form.component';

describe('UserDisplayFormComponent', () => {
  let component: UserDisplayFormComponent;
  let fixture: ComponentFixture<UserDisplayFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDisplayFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDisplayFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
