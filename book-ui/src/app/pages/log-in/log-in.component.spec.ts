import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginedComponent } from './log-in.component';

describe('LoginedComponent', () => {
  let component: LoginedComponent;
  let fixture: ComponentFixture<LoginedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
