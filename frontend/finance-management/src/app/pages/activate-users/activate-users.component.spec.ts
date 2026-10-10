import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivateUsersComponent } from './activate-users.component';

describe('ActivateUsersComponent', () => {
  let component: ActivateUsersComponent;
  let fixture: ComponentFixture<ActivateUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivateUsersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivateUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
