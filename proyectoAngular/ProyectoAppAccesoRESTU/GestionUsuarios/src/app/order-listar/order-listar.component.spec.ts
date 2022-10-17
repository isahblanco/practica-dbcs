import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderListarComponent } from './order-listar.component';

describe('OrderListarComponent', () => {
  let component: OrderListarComponent;
  let fixture: ComponentFixture<OrderListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderListarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
