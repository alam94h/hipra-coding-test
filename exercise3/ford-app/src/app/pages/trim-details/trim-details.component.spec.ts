import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrimDetailsComponent } from './trim-details.component';

describe('TrimDetailsComponent', () => {
  let component: TrimDetailsComponent;
  let fixture: ComponentFixture<TrimDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrimDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TrimDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
