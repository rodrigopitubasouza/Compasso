import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { ButtonModule } from 'src/app/components/button/button.module';
import { InputModule } from 'src/app/components/input/input.module';
import { TableModule } from 'src/app/components/table/table.module';

import { UserRepositoryComponent } from './user-repository.component';

describe('UserRepositoryComponent', () => {
  let fixture: ComponentFixture<UserRepositoryComponent>;
  let component: UserRepositoryComponent;
  let activatedRoute: ActivatedRoute;

  beforeEach(done => {
    TestBed.configureTestingModule({
      declarations: [UserRepositoryComponent],
      imports: [
        HttpClientTestingModule,
        InputModule,
        ButtonModule,
        TableModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              params: {
                param: 'teste'
              }
            }
          }
        }
      ]
    })
      .compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(UserRepositoryComponent);
        component = fixture.debugElement.componentInstance;
        activatedRoute = TestBed.inject(ActivatedRoute);
        fixture.detectChanges();
        done();
      });
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  
  it('ngOnInit should get username', () => {
    component.ngOnInit();
    expect(component.userName).toEqual('teste');
  });
});
