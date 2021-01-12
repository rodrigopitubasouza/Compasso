import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UsersService } from './users.service';
import { from } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppConstants } from 'src/app/shared/core/app-constants';

describe('UsersService', () => {
  let service: UsersService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(UsersService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

    it('Should return the user, if exist username', async () => {
        const dummyUser = {
          id: 1,
          name: 'teste 1',
          login: 'testeUsuarioExistente',
          public_repos: 1,
          followers: 1,
          following: 1
        }

        service.findByUserName('testeUsuarioExistente').subscribe(user => {
          expect(user).toEqual(dummyUser);
        })

        const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER).concat('/testeUsuarioExistente'));

        expect(request.request.method).toBe('GET');

        request.flush(dummyUser);
    });

    it('Should return empty, if not exist username', async () => {
      const dummyUser = [];

      service.findByUserName('usuarioTotalmenteInexistente').subscribe(user => {
        expect(user).toEqual([]);
      })

      const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER)
      .concat('/usuarioTotalmenteInexistente'));

      expect(request.request.method).toBe('GET');

      request.flush(dummyUser);
   });
});
