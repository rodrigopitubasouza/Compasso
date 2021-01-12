import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { from } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppConstants } from 'src/app/shared/core/app-constants';
import { UserStarredService } from './user-starred.service';

describe('UserRepositoryService', () => {
  let service: UserStarredService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(UserStarredService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

    it('Should return the stars for user, if exist username', async () => {
        const dummyStars = [{
          id: 1,
          name: 'teste 1',
          html_url: 'teste.com'
        },
        {
          id: 2,
          name: 'teste 2',
          html_url: 'teste2.com'
        }
      ];

        service.findByUserName('testeUsuarioExistente').subscribe(repos => {
          expect(repos.length).toBe(2);
          expect(repos).toEqual(dummyStars);
        })

        const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER)
        .concat('/testeUsuarioExistente').concat(AppConstants.STARRED_CONTROLLER));

        expect(request.request.method).toBe('GET');

        request.flush(dummyStars);
    });

    it('Should return empty, if not exist username', async () => {
      const dummyStars = [];

      service.findByUserName('usuarioTotalmenteInexistente').subscribe(stars => {
        expect(stars).toEqual([]);
      })

      const request = httpMock.expectOne(environment.applicationUrl.concat('/').concat(AppConstants.USER_CONTROLLER)
      .concat('/usuarioTotalmenteInexistente').concat(AppConstants.STARRED_CONTROLLER));

      expect(request.request.method).toBe('GET');

      request.flush(dummyStars);
   });
  });