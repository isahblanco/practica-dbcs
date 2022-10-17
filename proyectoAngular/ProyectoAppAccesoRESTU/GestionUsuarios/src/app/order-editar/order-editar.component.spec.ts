import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderEditarComponent } from './order-editar.component';

describe('OrderEditarComponent', () => {
  let component: OrderEditarComponent;
  let fixture: ComponentFixture<OrderEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
