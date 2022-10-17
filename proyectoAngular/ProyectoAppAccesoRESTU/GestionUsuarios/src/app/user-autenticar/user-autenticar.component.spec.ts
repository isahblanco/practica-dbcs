import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAutenticarComponent } from './user-autenticar.component';

describe('UserAutenticarComponent', () => {
  let component: UserAutenticarComponent;
  let fixture: ComponentFixture<UserAutenticarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAutenticarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAutenticarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
