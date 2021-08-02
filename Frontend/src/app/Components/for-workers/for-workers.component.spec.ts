import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForWorkersComponent } from './for-workers.component';

describe('ForWorkersComponent', () => {
  let component: ForWorkersComponent;
  let fixture: ComponentFixture<ForWorkersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForWorkersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ForWorkersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
